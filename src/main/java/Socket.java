import java.io.IOException;

import java.lang.*;

public class Socket {

    static class ThreadAccueil extends Thread{                  //Creation premier thread
        @Override
        public void run(){                                      //Reecriture du run pour ThreadAccueil
            try {
                Gestionhtml.GenererLiensAccueil();                        //Le run va lancer la génération de l'accueil ou afficher une erreur s'il ne peut pas

            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    static class ThreadAgent extends Thread{                    //Creation du deuxieme thread
        @Override
        public void run(){                                      //Reecriture du run pour ThreadAgent
            try {
                Gestionhtml.GenererPageAgent();                          //Le run va lancer la génération des agents ou afficher une erreur s'il ne peut pas
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }
    static class ThreadPassword extends Thread{                    //Creation du troisème thread
        @Override
        public void run(){                                      //Reecriture du run pour ThreadAgent
            try {
                Gestionhtml.genererHtAccess();                          //Le run va lancer la génération des agents ou afficher une erreur s'il ne peut pas
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    public static void InitThread(){
        Thread tAccueil = new Thread(new ThreadAccueil());      //Initialisation des threads
        Thread tAgent = new Thread(new ThreadAgent());
        Thread tPassword = new Thread(new ThreadPassword());

        tAccueil.start();                                       //Lancement des threads
        tAgent.start();
    }
}
