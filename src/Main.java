public class Main {

    public static void main(String[] args) {
        //StartScreen start = new StartScreen();
        playBoard Board = new playBoard();
    }

}


/*

--> true pl1 || false pl2

--> phase 3

--> features

phase 1:    wenn eine Mühle geschlossen wird während es nur eine weitere Mühle
            gibt dann kommt das Spiel zum halt und kann nicht fortgesetzt werden
            -> https://youtu.be/clSXp-__jLU

phase 2:    playerchange nach mühle funkt net
            test steine nur auf mögliche psoitionen (also auf eine andere pos wo bereits ein stein ist)
            nur von dem spieler den stein bewegen
            nur bis minimal 6 steine um zur phase 3 zu kommen (springen)
            entfernen von steinen aus mühlen wenn alle nur in mühlen sind
            Siegesbedingungen ausnahme fälle: --> gewinnen durch einschließen
                                           --> gewinnen durch zu wenig steine
                                           --> unentschieden nach 20 zügen ohne mühle
                                           --> unentschieden wenn 3 mal in folge die gleiche Stellung der Spielsteine erreicht wird

*/
