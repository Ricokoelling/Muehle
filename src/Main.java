import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        playBoardClient pbc = new playBoardClient(true);
        //playerBoardClientTest pbct = new playerBoardClientTest(true);
    }

}


/*

TODO:   logic beenden, also phase 2 und 3 und Gewinnzustände
        reset einfügen sodass man das jederzeit machen kann
        Wichtig!!!! wenn ein Spieler wartet kann dieser auf eine Position klicken und dann wird der click gespeichert und wenn dieser wieder dran ausgeführt. Das kann man auch stacken....
            --> mit einer Variable blocken funktioniert nicht
            --> den mouselistener zu entfernen und dann wieder hinzuzufügen geht auch nicht
            --> ich habe keine Ahnung wie man das fixen kann
        remove funktioniert leider auch nicht
        übergang in phase 2 funktioniert nicht ordentlich
        Max: Bitte lass die Serververbindung erstmal so wie es ist, es funktioniert ohne fehler und die logic ist leicht hinzugefügt


state´s:    1: do phase 1
            2: do phase 0
            3: know the other player does phase 0
            4: remove in other player field

 */

