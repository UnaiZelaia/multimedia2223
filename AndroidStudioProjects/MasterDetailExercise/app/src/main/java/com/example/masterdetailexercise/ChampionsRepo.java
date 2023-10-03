package com.example.masterdetailexercise;

import java.util.ArrayList;
import java.util.List;

public class ChampionsRepo {

    List<Champion> champions = new ArrayList<>();

    public interface Callback{
        void whenFinish(List<Champion> champions);
    }

    ChampionsRepo(){
        champions.add(new Champion(R.drawable.jhin_champion, "Jhin", "Bot lane", "Ionia", 51.6f, "Jhin is a meticulous criminal psychopath who believes murder is art. Once an Ionia Crest icon Ionian prisoner, but freed by shadowy elements within Ionia's ruling council, the serial killer now works as their cabal's assassin. Using his gun gun as his paintbrush, Jhin creates works of artistic brutality, horrifying victims and onlookers. He gains a cruel pleasure from putting on his gruesome theater, making him the ideal choice to send the most powerful of messages: terror. "));
        champions.add(new Champion(R.drawable.leona_champion, "Leona", "Support", "Mount targon", 50.7f, "Imbued with the fire of the sun, Leona Leona is a holy warrior of the 03MT082-full Solari who defends Targon Crest icon Mount Targon with her Zenith Blade Zenith Blade and the Shield of Daybreak Shield of Daybreak. Her skin shimmers with starfire while her eyes burn with the power of the celestial Aspect within her. Armored in gold and bearing a terrible burden of ancient knowledge, Leona brings enlightenment to some, death to others. "));
        champions.add(new Champion(R.drawable.milio_champion, "Milio", "Support", "Ixtal", 54.9f, "Milio is a warmhearted boy from Ixtal who has, despite his young age, mastered the fire axiom and discovered something new: soothing fire. With this newfound power, Milio plans to help his family escape their exile by joining the Yun Tal—just like his grandmother once did. Having traveled through the Ixtal jungles to the capital of Ixaocan, Milio now prepares to face the Vidalion and join the Yun Tal, unaware of the trials—and dangers—that await him. "));
        champions.add(new Champion(R.drawable.ksante_champion, "K'sante", "Top lane", "Shurima", 46.9f, "Defiant and courageous, K'Sante battles colossal beasts and ruthless Ascended to protect his home of Nazumah, a coveted oasis amid the sands of Shurima. But after a falling-out with his former partner, K'Sante realizes that in order to become a warrior worthy of leading his city, he must temper his single-minded drive for success. Only then can he avoid falling prey to his own pride and find the wisdom he needs to defeat the vicious monsters threatening his people. "));
        champions.add(new Champion(R.drawable.gragas_champion, "Gragas", "Jungle", "Freljord", 48.9f, "Equal parts jolly and imposing, Gragas Gragas is a massive, rowdy brewmaster on his own quest for the perfect pint of ale. Hailing from parts unknown, he now searches for rare ingredients among the unblemished wastes of the Freljord Crest icon Freljord, trying each recipe as he goes. Often intoxicated and extremely impulsive, he is legendary for the brawls he starts, which often end in all-night parties and widespread property damage. Any appearance from Gragas must surely foreshadow drinking and destruction—in that order. "));
        champions.add(new Champion(R.drawable.ashe_champion, "Ashe", "Bot lane", "Freljord", 51.7f, "Iceborn warmother of the Avarosan tribe, Ashe commands the most populous horde in the north. Stoic, intelligent, and idealistic, yet uncomfortable with her role as leader, she taps into the ancestral magics of her lineage to wield a bow of True Ice. With her people’s belief that she is the mythological hero Avarosa reincarnated, Ashe hopes to unify the Freljord once more by retaking their ancient, tribal lands."));
    }

    public List<Champion> get(){ return champions;}

    public void insert(Champion champion, Callback callback) {
        champions.add(champion);
        callback.whenFinish(champions);
    }

    public void delete(Champion champion, Callback callback) {
        champions.remove(champion);
        callback.whenFinish(champions);
    }

}
