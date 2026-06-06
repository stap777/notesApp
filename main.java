import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


class note {
    String title;
    String content;
    String category;
    LocalDateTime timestamp;

    public note(String a, String b, String c) {
        title = a;
        content = b;
        category = c;
        timestamp = LocalDateTime.now();
    }

    public String toString() {
        return title + "  |  " + content + "  |  " + category +"  |  " + timestamp;
    }
}

class NoteApp {

    ArrayList<note> notes = new ArrayList<>();

    void addNote(Scanner sc) {
        System.out.println("note title: ");
        String title = sc.nextLine();
        System.out.println("note content: ");
        String content = sc.nextLine();
        System.out.println("note category: ");
        String category = sc.nextLine();

        note c = new note(title, content, category);

        notes.add(c);
    }

    void viewNotes(Scanner sc) {
        for (int i = 0; i < notes.size(); i++) {
            System.out.println(notes.get(i).title + "    |    " + notes.get(i).timestamp);
        }
    }
}

class main {


    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);

        NoteApp app = new NoteApp();

        ArrayList<String> menu = new ArrayList<>();

        menu.add("(1) add note");
        menu.add("(2) view notes");

        while (true) {
            for (int i = 0; i < menu.size(); i++) {
                System.out.println(menu.get(i));
            }

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) {
                app.addNote(sc);
            }
            else if (c == 2) {
                app.viewNotes(sc);
            }
        }

    }
}