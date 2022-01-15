import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        playBoardClient pbc = new playBoardClient(true);
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
            5: change to phase while the other player just placed a stone
            6: knows phase changed but its not their turn
            7: move stone on field
            8: remove stone and still stay in right phase
            9: remove in other player field and changes back to phase 2
            10: Phase change to 3 während der andere spieler gerade einen stein removed hat
            11: knows phase change but its not their turn
            12: jump on field
            13: jumps on his field but knows he will get back a move

 */

