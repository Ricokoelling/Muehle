import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        playBoardClient pbC = new playBoardClient(true);
    }

}


/*

TODO:   vom Server aus an beide clients um repaint zu ermöglichen oder es irgendwie lösen sodass es lokal gemacht wird.
        nur phase 1 angefangen und diese funktioniert noch nicht ansatzweise, beide können immernoch gleichzeitig placen und die position ist egal.... -->
        logic muss auf dem Server sein, steht so im Meilenstein also muss das auch noch gemacht werden (auch das wenn etwas passiert (zB gleicher stein) sollte eben halt der Server merken: yo is was falsch
        panel fixen (Daniel E hat paar ansätze geliefert)


 */

