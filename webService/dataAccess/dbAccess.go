package dataAccess

import (
	"context"
	"database/sql"
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/gin-gonic/gin"
	_ "github.com/lib/pq"
)

var dbUser = "odoo"
var dbPassword = "123"
var database = "odoodb"
var serverAddress = "192.168.122.14"
var serverPort = "5432"
var apiUser = "unai"
var apiSecret = "com123"

var connectString = fmt.Sprintf("host=%s port=%s user=%s password=%s dbname=%s sslmode=disable", serverAddress, serverPort, dbUser, dbPassword, database)

var db *sql.DB

func DbConnect() *sql.DB {
	var err error

	db, err = sql.Open("postgres", connectString)

	if err != nil {
		log.Fatal("Error al establecer conexión", err.Error())
		ctx := context.Background()
		err = db.PingContext(ctx)
		if err != nil {
			log.Fatal(err.Error())
		}
	}
	return db
}

type Client struct {
	Id         *string
	Name       *string
	Email      *string
	Phone      *string
	Mobile     *string
	Street     *string
	PostalCode *string
	City       *string
}

func Clients(c *gin.Context) {

	user := c.PostForm("user")
	password := c.PostForm("password")

	if user == apiUser && password == apiSecret {
		ctx := context.Background()
		db = DbConnect()

		err := db.PingContext(ctx)

		if err != nil {
			println(err.Error())
		}

		sqlQuery := "SELECT Id, COALESCE(name, '') as name, COALESCE(email, '') as email, COALESCE(phone, '') as phone, COALESCE(mobile, '') as mobile, COALESCE(street, '') as street, COALESCE(zip, '') as postalCode, COALESCE(city, '') as city FROM res_partner WHERE customer_rank > 0"

		rows, err := db.QueryContext(ctx, sqlQuery)

		if err != nil {
			println(err.Error())
		}

		defer rows.Close()

		var count int = 0

		results := make([]Client, 0)

		for rows.Next() {
			var id, name, phone, mobile, email, street, postalCode, city string

			err := rows.Scan(&id, &name, &email, &phone, &mobile, &street, &postalCode, &city)
			if err != nil {
				println(err.Error())
			}

			resultStruct := Client{&id, &name, &email, &phone, &mobile, &street, &postalCode, &city}

			results = append(results, resultStruct)
			count++
			if err != nil {
				println(err.Error())
			}
		}

		c.IndentedJSON(http.StatusOK, results)

	} else {
		c.IndentedJSON(http.StatusUnauthorized, "User not authorized")
	}
}

type Product struct {
	Name      *string
	Price     *string
	Stock     *string
	Id        *string
	SaleDelay *float32
}

func Products(c *gin.Context) {
	user := c.PostForm("user")
	password := c.PostForm("password")

	if user == apiUser && password == apiSecret {
		ctx := context.Background()
		db = DbConnect()

		err := db.PingContext(ctx)

		if err != nil {
			println(err.Error())
		}

		sqlQuery := "SELECT t.name->>'es_ES' as name, COALESCE(t.list_price, 0.00) as price, COALESCE(q.quantity, 0) as stock, t.Id, COALESCE(t.sale_delay, 0.00) FROM product_template t JOIN stock_quant q ON q.product_id = t.Id WHERE q.inventory_date IS NOT NULL AND t.sale_ok = true;"

		rows, err := db.QueryContext(ctx, sqlQuery)

		if err != nil {
			println(err.Error())
		}

		defer rows.Close()

		var count int = 0

		results := make([]Product, 0)

		for rows.Next() {
			var name, phone, mobile, email string
			var saleDelay float32

			err := rows.Scan(&name, &email, &phone, &mobile, &saleDelay)
			if err != nil {
				println(err.Error())
			}

			resultStruct := Product{&name, &email, &phone, &mobile, &saleDelay}

			results = append(results, resultStruct)
			count++
			if err != nil {
				println(err.Error())
			}
		}

		c.IndentedJSON(http.StatusOK, results)

	} else {
		c.IndentedJSON(http.StatusUnauthorized, "User not authorized")
	}
}

// Detalles de factura: sale_order(une con res_partner en partner_id -> id): name (id de factura), invoice_status(), amount_unpaid, delivery_status, date_order(fecha de creación de factura), amount_untaxed, amount_tax
// Lineas de factura: sale_order_line(une con sale_order en order_id -> id): name(nombre de producto), qty_invoiced(cantidad del producto)
type Bill struct {
	Id             *int
	PartnerId      *int
	BillName       *string
	InvoiceStatus  *string
	AmountUnpaid   *float64
	DeliveryStatus *string
	DateOrder      *time.Time
	AmountUntaxed  *float64
	AmountTax      *float64
	ProductName    *string
	QtyInvoiced    *float64
}

func Bills(c *gin.Context) {
	user := c.PostForm("user")
	password := c.PostForm("password")

	if user == apiUser && password == apiSecret {

		ctx := context.Background()
		db = DbConnect()

		err := db.PingContext(ctx)

		if err != nil {
			println(err.Error())
		}

		// Detalles de factura: sale_order(une con res_partner en partner_id -> id): name (id de factura), invoice_status(), amount_unpaid, delivery_status, date_order(fecha de creación de factura), amount_untaxed, amount_tax
		// Lineas de factura: sale_order_line(une con sale_order en order_id -> id): name(nombre de producto), qty_invoiced(cantidad del producto)
		sqlQuery := "SELECT o.id, o.partner_id, COALESCE(o.name, '') as name, COALESCE(o.invoice_status, '') as invoice_status, COALESCE(o.amount_unpaid, 0.00) as amount_unpaid, COALESCE(delivery_status, '') as delivery_status, o.date_order, COALESCE(o.amount_untaxed, 0.00) as amount_untaxed, COALESCE(o.amount_tax, 0.00) as amount_tax,COALESCE(l.name, '') as product_name, COALESCE(l.qty_invoiced, 0.00) as quant_invoiced FROM sale_order o INNER JOIN sale_order_line l ON l.order_id = o.id;"

		rows, err := db.QueryContext(ctx, sqlQuery)

		if err != nil {
			println(err.Error())
		}

		defer rows.Close()

		var count int = 0

		results := make([]Bill, 0)

		for rows.Next() {
			var billName, invoiceStatus, deliveryStatus, productName string
			var amountUnpaid, amountUntaxed, amountTax, qtyInvoiced float64
			var dateOrder time.Time
			var id, partnerId int

			err := rows.Scan(&id, &partnerId, &billName, &invoiceStatus, &amountUnpaid, &deliveryStatus, &dateOrder, &amountUntaxed, &amountTax, &productName, &qtyInvoiced)
			if err != nil {
				println(err.Error())
			}

			resultStruct := Bill{&id, &partnerId, &billName, &invoiceStatus, &amountUnpaid, &deliveryStatus, &dateOrder, &amountUntaxed, &amountTax, &productName, &qtyInvoiced}

			results = append(results, resultStruct)
			count++
			if err != nil {
				println(err.Error())
			}
		}
		c.IndentedJSON(http.StatusOK, results)
	} else {
		c.IndentedJSON(http.StatusUnauthorized, "User not authorized")
	}
}
