import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public note(String a, String b, String c, LocalDateTime timestamp) {
        this.title = a;
        this.content = b;
        this.category = c;
        this.timestamp = timestamp;
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
        while (true) {

            System.out.println("-----------------------------------------------------------------------------------");

            for (int i = 0; i < notes.size(); i++) {
                System.out.println(i + 1 + ") " + notes.get(i).title + "    |    " + notes.get(i).timestamp);
            }

            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println((notes.size() + 1) + ") " + "to filter by category");

            System.out.println("-----------------------------------------------------------------------------------");

            System.out.println((notes.size() + 2) + ") " + "to search");
            System.out.println("-----------------------------------------------------------------------------------");
    
            System.out.println("-1) to exit");

            System.out.println("-----------------------------------------------------------------------------------");

            int c = sc.nextInt();
            sc.nextLine();

            if (c - 1 < notes.size() && c >= 1) {
                System.out.println(notes.get(c - 1));
            }
            else if (c == notes.size() + 1) {
                System.out.println("category: ");
                String cat = sc.nextLine();
                boolean isvalid = false;
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).category.equalsIgnoreCase(cat)) {
                        System.out.println(i + 1 + ") " + notes.get(i).title + "    |    " + notes.get(i).timestamp);
                        isvalid = true;
                    }
                }
                System.out.println("-----------------------------------------------------------------------------------");
                if (!isvalid) {
                    System.out.println("cateory not foind");
                }
            }
            else if (c == notes.size() + 2) {
                System.out.println("Title: ");
                String Title = sc.nextLine();
                boolean isvalid = false;
                for (int i = 0; i < notes.size(); i++) {
                    if (notes.get(i).title.equalsIgnoreCase(Title)) {
                        System.out.println(notes.get(i));
                        isvalid = true;
                        break;
                    }
                }
                if (!isvalid) {
                    System.out.println("title not found");
                }
            }
            else if (c == -1) {
                break;
            }    
            else {
                System.out.println("-----------------------------------------------------------------------------------"); 
            }
        }
        
    }

    void saveFile() {
        try {
            FileWriter fw = new FileWriter("notes.txt");

            for (int i = 0; i < notes.size(); i++) {
                note n = notes.get(i);
                String line = n.title + "|" + n.content + "|" + n.category + "|" + n.timestamp;
                fw.write(line + "\n");
            }
            

            fw.close();
        }
        catch (IOException e) {
            System.out.println("error occured");
        }
    }

    void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("notes.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                note n = new note(parts[0], parts[1], parts[2], LocalDateTime.parse(parts[3]));
                notes.add(n);
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("error occured");
        }
    }

    void removeNote(Scanner sc) {
        for (int i = 0; i < notes.size(); i++) {
            System.out.println(i + 1 + ") " + notes.get(i).title + "    |    " + notes.get(i).timestamp);
        }
        System.out.println("Remove: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index <= notes.size() && index >= 0) {
            notes.remove(index - 1);
        }
        else {
            System.out.println("invalid input");
        }
    }
}

class main {


    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in);

        NoteApp app = new NoteApp();

        app.loadFile();

        ArrayList<String> menu = new ArrayList<>();

        menu.add("(1) add note");
        menu.add("(2) view notes");
        menu.add("(3) delete Note");
        menu.add("(4) exit");
        

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
            else if (c == 3) {
                app.removeNote(sc);
            }
            else if (c == 4) {
                break;
            }
            else {
                System.out.println("invalid input");
            }
        }

        app.saveFile();

    }
}