public class Main {

    public static void main(String[] args) {
        //StartScreen start = new StartScreen();
        playBoard Board = new playBoard();
    }

}


/*
maybe like this but try out if we got time (müssen alles rewritten)
positions new: [pos on horizontal, pos in depth*, ring number] * 0 means its the highest point, 1 is the middle , 2 is down
        1 ->[0,0,0]
        2 ->[1,0,0]
        3 ->[2,0,0]
        4 ->[0,0,1]
        5 ->[1,0,1]
        6 ->[2,0,1]
        7 ->[0,0,2]
        8 ->[1,0,2]
        9 ->[2,0,2]
        10->[0,1,0]
        11->[0,1,1]
        12->[0,1,2]
        13->[1,1,2]
        14->[1,1,1]
        15->[1,1,0]
        16->[0,2,2]
        17->[1,2,2]
        18->[2,2,2]
        19->[0,2,1]
        20->[1,2,1]
        21->[2,2,1]
        22->[0,2,0]
        23->[1,2,0]
        24->[2,2,0]

--> true pl1 || false pl2

--> phase 3

--> features

--> doppel muehlen sollten jetzt funktionieren, aber nicht dreifachmuehlen. dafür müssten wir die überprüfung besser machen, wir zeigen es einfach sickert nicht mit mehr als ner doppel muehle

phase 1:    -> https://youtu.be/clSXp-__jLU
            das video ist privat

phase 2:    nur bis minimal 6 steine um zur phase 3 zu kommen (springen)
            Siegesbedingungen ausnahme fälle:   --> gewinnen durch einschließen //should work even in phase 1
                                                --> gewinnen durch zu wenig steine  // leicht zu machen
                                                --> unentschieden nach 20 zügen ohne mühle //zug counter (extra machen maybe als schwierigkeit sonst haben die andern das nicht)
                                                (nachfragen ob die anderen das haben, wird nicht in den regeln von dem blatt erwähnt)


*/
