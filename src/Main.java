public class Main {

    public static void main(String[] args) {
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

--> features

--> ich hab das label kleiner gemacht sonst gibt es bei 1920x1080 probleme.
--> es sollte jetzt alles funktionieren müssen aber noch mehr testen um nicht noch bugs zu finden
--> nach dem jump muss man zweimal ziehen um den stein zu bewegen. why? maybe das noch lösen
--> features einbauen:
                        --> maybe bissle den hintergrund ändern damit es besser aussieht.
                        --> undo button
                        --> reset button
                        --> player stones left
                        --> more?

-- startscreen
--options
++ size 1920x1080
maybe undo butteon
reset button maxa
    -> Anpassung der größe und Ort sieht scheiße aus und zerstört den Code




*/
