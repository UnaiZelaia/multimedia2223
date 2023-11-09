package main

import (
	"webService/erronkaApi/dataAccess"

	"github.com/gin-gonic/gin"
)

func startEndpoints(router *gin.Engine) {

	router.POST("/Clients", dataAccess.Clients)
	router.POST("/Products", dataAccess.Products)
	router.POST("/Bills", dataAccess.Bills)

}
func main() {
	address := "0.0.0.0:8000"
	router := gin.Default()
	go startEndpoints(router)
	router.Run(address)
}
