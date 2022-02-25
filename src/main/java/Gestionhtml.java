import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Gestionhtml {


    public static void GenererLiensAccueil() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt")); //on ouvre un lecteur du fichier staff.txt
        String str;

        List<String> list = new ArrayList<String>(); // création d'une liste de chaine de caractère
        while ((str = in.readLine()) != null)  // tant que l'on a des élément à lire dans le fichier staff.txt
        {
            list.add(str); // on ajoute l'élement courant (ici un identifiant) a notre liste
        }

        java.util.Collections.sort(list); // on trie notre liste par ordre alphabétique
        String AllHref = "";

        for (int i = 0; i < list.size(); i++) // tant qu'il y'a des élément dans notre liste :
        {
            list.set(i, "<a href=\"Agents/" + list.get(i) + ".html\">" + list.get(i) + "</a></br>"); // on modifie l'élément courant de notre liste. exemple : l'element cberthier devient <a href="Agents/cberthier.html">cberthier</a></br>
            AllHref = AllHref + list.get(i) + "\n"; // on ajoute l'élement courant à cette chaine de caractère
        }

        //AllHref contient maintenant tout les lien html de nos Agents

        InsertionHTML("StrtoReplace",AllHref); // on insert nos lien dans notre page accueil
        }

        public static void  InsertionHTML(String AncienneVar, String HTMLInsert) throws IOException // insère l des pages html de nos agent dans le fichier Accueil.html
        {

            File HTMLSqueletteAccueil = new File("src/main/Html/Acc/Accueil.html");// on ouvre ou créer un fichier  Accueil.html vide dans le répertoire Acc
            File HTMLGenerated = new File("src/main/Html/Generation/Accueil.html");// on ouvre ou creer un fichier  Accueil.html vide dans le répertoir Génération, ce sera notre accueil principal
            String AncienHTML = "";
            BufferedReader reader = new BufferedReader(new FileReader(HTMLSqueletteAccueil)); // on ouvre le fichier Accueil.html du répertoire Acc

            String line = reader.readLine(); // on recupere la premiere ligne de notre fichier
            while (line != null) // tant qu'il y a des éléments sur la ligne
            {
                AncienHTML = AncienHTML + line + System.lineSeparator(); //on recupere la ligne du fichier
                line = reader.readLine(); // on actualise la ligne
            }

            String NouveauHTML = AncienHTML.replaceAll(AncienneVar, HTMLInsert); // on remplace l'ancienne chaine de caractère (strToreplace) avec notre chaine contenant les id du fichier HTML insert

            FileWriter writer = new FileWriter(HTMLGenerated); // on ouvre notre fichier accueil.html du répertoire Génération en écriture
            writer.write("");
            writer.write(NouveauHTML);// on insrit tout nos liens dans ce fichier html
            reader.close(); // fermeture reader
            writer.close(); // fermeture writer


        }


    public static void SupprimmerAgentsAvantMAJ()// on supprime tout les fichier HTML
    {
        File DossierAgents = new File("src/main/Html/Generation/Agents");

        File[] ListeFichiersAgents = DossierAgents.listFiles();
        for(File file : ListeFichiersAgents){
            System.out.println("Suppresion de  "+ file.getName());
            file.delete();
        }

    }

    public static void GenererPageAgent() throws IOException // génère la page html du chacun des agent présent dans le fichier staff.txt
    {
        BufferedReader in = new BufferedReader(new FileReader("src/main/Txt/staff.txt")); // on ouvre ce fichier staff.txt en lecture, contenant la liste des identifiant des agents
        String str;

        List<String> list = new ArrayList<String>(); // on créer un liste de chaine de caractère
        SupprimmerAgentsAvantMAJ(); // On supprime l'ensemble des fichier html déja existant
        while ((str = in.readLine()) != null) //tant que l'on peut lire des élément dans staff.txt
        {
            list.add(str);  // on ajoute dans notre liste l'identifiant de notre agent
        }

        java.util.Collections.sort(list); // on trie la liste par ordre alphabétique
        String AllHref = "";

        for (int i = 0; i < list.size(); i++) // pour chaque id d'agent dans notre liste :
        {
            File HTMLGenerated = new File("src/main/Html/Generation/Agents/" + list.get(i) + ".html"); // on créer le fichier html de l'agent
            FileWriter writer = new FileWriter(HTMLGenerated); // on ouvre ce fichier en écriture
            writer.write(""+list.get(i));  // on ecrit son identifiant
            String photo = "../../../../../img/"+ list.get(i) + ".jpg";  // recupere le répertoire courant de sa photo d'identité
            writer.write("<div style=\"text-align: right;\">\n" +
                   "<img src= \""+photo+"\" height=\"200px\" width=\"250px\" />" + "</div>"); //on insere cette image dans la page html de l'agent

            writer.write("<br><br><br><br><br><br><br>"); // j'ajoute des espacements
            List<String> materiel = decomposeMateriel(list.get(i)); // on récupere la liste de matériel de notre agent
            writer.write("<div style=\"text-align: center;\">" ); // on ouvre un div, on centre les éléments suivant
            for (int s = 0 ; s < materiel.size(); s ++) // pour tout les éléments présent dans notre liste de matériel
            {
                writer.write("<li>"+materiel.get(s)+"</li>"); // on ecrit tout les materiels de notre agent, à la suite
            }
            writer.write("</div>" ); // on ferme la div
            writer.close();// on arrete d'écrire dans  notre fichier html
        }

    }

    /*
     String id : id d'un agent, exemple : cberthier
     return une liste contenant tout l'équipement affilié à un agent
     */
    public static List<String> decomposeMateriel (String id ) throws IOException
    {
        String fileName = "src/main/Txt/"+ id + ".txt"; // recuperer le répertoire courant du fichier .txt de notre agent
        BufferedReader in = new BufferedReader(new FileReader(fileName)); // on ouvre ce fichier en lecture
        String str;


        List<String> list = new ArrayList<String>(); // on créer une liste de chaine de caractère
        while ((str = in.readLine()) != null) {
            list.add(str); // cette liste contiendra tout les elements du fichier .txt de notre agent
        }


        BufferedReader in2 = new BufferedReader(new FileReader("src/main/Txt/liste.txt"));
        String str2;

        List<String> listtt = new ArrayList<String>(); // cette liste comprendra tout les elements (équipements) du fichier liste.txt


        List<String> materielEmploye = new ArrayList<String>(); // cette liste comprendra tout les materiel d'un employé

        while ((str2 = in2.readLine()) != null)  // tant que l'on peut lire des élément dans le fichier liste.txt
        {
            listtt.add(str2); // on ajoute ces éléments à notre liste
        }

        for (int i = 0 ; i <list.size() ; i ++) // tant que l'on peut lire des élément du fichier .txt de notre agent
        {
            String sa = list.get(i); // on ajoute ces elements dans la liste

            for (int j = 0 ; j < listtt.size(); j++)// pour tout les element (équipement) de notre luste
            {
                if (sa.equals(Firstmot(listtt.get(j)))) // on compare l'élément courant de la liste contenant les éléments de l'agent avec l'élément courant de la liste contenant tout les équipements de liste.txt
                {
                    materielEmploye.add(Lastmot(listtt.get(j))) ; // si les 2 donc similaire, on ajoute dans la liste final l'équipement correspondant. exmple : on ajoute l'élément Porte menottes si le fichier .txt de notre agent contient l'équipement menottes
                }
            }
        }

        return materielEmploye ; // on renvoie une liste contenant le bon équipement de notre agent
    }



    public static String Lastmot(String s) // retourne le dernier mot d'un chaine de caractere.
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

    public static String Firstmot(String s) // retourne le premier mot d'un chaine de caractere.
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


    public static void genererHtAccess() throws IOException {
        //CREATION MDP DANS FICHIER HTPASSWD et Creation du fichier HTACCES
        try {
            BufferedReader lectureHtAccess;
            String ligneHtAccess;
            BufferedReader lectureAgentStuff;
            String ligneAgentStuff;

            File fichierHtAccess = new File("src/main/Html/Generation/Agents/.htaccess");
            if (!fichierHtAccess.exists()) {                                                              //Si le fichier n'existe pas, création de ce dernier
                fichierHtAccess.createNewFile();
            }
            File fichierHtPassword = new File("src/main/Html/Generation/Agents/.htpasswd");
            if (!fichierHtPassword.exists()) {                                                              //Si le fichier n'existe pas, création de ce dernier
                fichierHtPassword.createNewFile();
            }

            FileWriter fichierEcritureAccess1 = new FileWriter(fichierHtAccess.getAbsoluteFile(), StandardCharsets.UTF_8);             //Recuperation du chemin du fichier
            BufferedWriter bwAccess1 = new BufferedWriter(fichierEcritureAccess1);
            bwAccess1.write("AuthName \"Zone Securisee\"\n" +
                    "AuthType Basic\n" +
                    "AuthUserFile \"/mnt/jenkins/agents/.htpasswd\"\n");

            lectureHtAccess = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/Txt/staff.txt"), StandardCharsets.UTF_8));   //Recuperation du chemin du fichier
            while ((ligneAgentStuff = lectureHtAccess.readLine()) != null) {
                bwAccess1.write("<FilesMatch \"" + ligneAgentStuff + ".html\">\n" +      //Ecriture de chaque user dans le htaccess
                        " Require user " + ligneAgentStuff + "\n" +
                        "</FilesMatch>\n");
            }
            bwAccess1.close(); // Fermeture du buffer pour validation de l'ecriture

            FileWriter fichierEcritureAccess2 = new FileWriter(fichierHtPassword.getAbsoluteFile(), StandardCharsets.UTF_8);  //Recuperation du chemin du fichier
            BufferedWriter bwAccess2 = new BufferedWriter(fichierEcritureAccess2);
            lectureHtAccess = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/Txt/staff.txt"), StandardCharsets.UTF_8));

            while ((ligneHtAccess = lectureHtAccess.readLine()) != null) {
                bwAccess2.write(ligneHtAccess + ":");                                                  //ajout user dans fichier htaccess
                lectureAgentStuff = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/Txt/" + ligneHtAccess + ".txt"), StandardCharsets.UTF_8));       //Lecture du fichier txt qui appartient à la personne
                int i = 0;
                while ((ligneAgentStuff = lectureAgentStuff.readLine()) != null) {
                    if (i == 3) {
                        String encodedString = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA1").digest(ligneAgentStuff.getBytes(StandardCharsets.UTF_8)));   //Encodage du mot de passe en sha-1 Base64
                        bwAccess2.write("{SHA}" + encodedString + "\n");  //on ecrit les mots de passe dans le fichier .htpasswd
                    }
                    i++;
                    //done
                }
            }
            bwAccess2.close(); // Fermeture du buffer pour validation de l'ecriture
        } catch (FileNotFoundException | NoSuchAlgorithmException exc) {    //Si il y a une erreur, envoie une erreur différente que de la page index
            exc.printStackTrace();
        }
    }




}