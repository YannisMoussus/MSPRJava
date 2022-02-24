import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Gestionhtml {







    public String GenererLiensAccueil() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }

        java.util.Collections.sort(list);
        String AllHref = "";

        for (int i = 0; i < list.size(); i++) {
            list.set(i, "<a href=\"Agents/" + list.get(i) + ".html\">" + list.get(i) + "</a></br>");
            AllHref = AllHref + list.get(i) + "\n";
        }

        return AllHref;
        }

        public void InsertionHTML(String AncienneVar, String HTMLInsert) throws IOException {

            File HTMLSqueletteAccueil = new File("src/main/Html/Acc/Accueil.html");
            File HTMLGenerated = new File("src/main/Html/Generation/Accueil.html");
            String AncienHTML = "";
            BufferedReader reader = new BufferedReader(new FileReader(HTMLSqueletteAccueil));

            String line = reader.readLine();
            while (line != null)
            {
                AncienHTML = AncienHTML + line + System.lineSeparator();
                line = reader.readLine();
            }

            String NouveauHTML = AncienHTML.replaceAll(AncienneVar, HTMLInsert);

            FileWriter writer = new FileWriter(HTMLGenerated);
            writer.write("");
            writer.write(NouveauHTML);
            reader.close();
            writer.close();


        }


    public void SupprimmerAgentsAvantMAJ(){
        File DossierAgents = new File("src/main/Html/Generation/Agents");

        File[] ListeFichiersAgents = DossierAgents.listFiles();
        for(File file : ListeFichiersAgents){
            System.out.println("Suppresion de  "+ file.getName());
            file.delete();
        }

    }

    public void GenererPageAgent() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt"));
        String str;

        List<String> list = new ArrayList<String>();
        SupprimmerAgentsAvantMAJ();
        while ((str = in.readLine()) != null)
        {
            list.add(str);
        }

        java.util.Collections.sort(list);
        String AllHref = "";

        for (int i = 0; i < list.size(); i++)
        {
            File HTMLGenerated = new File("src/main/Html/Generation/Agents/" + list.get(i) + ".html");
            FileWriter writer = new FileWriter(HTMLGenerated);
            writer.write(""+list.get(i));
            String photo = "../../../../../img/"+ list.get(i) + ".jpg";
            writer.write("<div style=\"text-align: right;\">\n" +
                   "<img src= \""+photo+"\" height=\"200px\" width=\"200px\" />" + "</div>");

            writer.write("<br><br><br><br><br><br><br>");
            List<String> materiel = decomposeMateriel(list.get(i));
            writer.write("<div style=\"text-align: center;\">" );
            for (int s = 0 ; s < materiel.size(); s ++)
            {
                writer.write("<li>"+materiel.get(s)+"</li>");
            }
            writer.write("</div>" );
            writer.close();
        }

    }


    public List<String> decomposeMateriel (String nom ) throws IOException {
        String fileName = "src/main/Txt/"+ nom + ".txt";
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str;

        // cette liste comprend tout les element du fichier "nomEmployé".txt
        List<String> list = new ArrayList<String>();
        while ((str = in.readLine()) != null) {
            list.add(str);
        }


        BufferedReader in2 = new BufferedReader(new FileReader("src/main/Txt/liste.txt"));
        String str2;
        // cette liste comprend tout les element du fichier liste.txt
        List<String> listtt = new ArrayList<String>();

        // cette liste comprendra tout les materiel d'un employé
        List<String> materielEmploye = new ArrayList<String>();

        while ((str2 = in2.readLine()) != null)
        {
            listtt.add(str2);
        }

        for (int i = 0 ; i <list.size() ; i ++)
        {
            String sa = list.get(i);

            for (int j = 0 ; j < listtt.size(); j++)
            {
                if (sa.equals(Firstmot(listtt.get(j))))
                {
                    materielEmploye.add(Lastmot(listtt.get(j))) ;
                }
            }
        }

        return materielEmploye ;
    }



    public static String Lastmot(String s)
    {
        String materiel = "" ;

        for (int i = 0; i < s.length() ; i ++)
        {
            if (s.charAt(i) == ' ')
            {
                materiel = s.substring(i+1);
                return materiel ;
            }
        }



        return materiel;
    }

    public static String Firstmot(String s)
    {
        String materiel = "" ;

        for (int i = 0; i < s.length() ; i ++)
        {
            if (s.charAt(i) != ' ')
            {
                materiel = materiel + s.charAt(i);
            }
            else
            {
                return materiel;
            }
        }



        return materiel;
    }




}