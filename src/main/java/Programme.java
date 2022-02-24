import java.io.IOException;

public class Programme {

    public static void main(String[] args) throws IOException {

        Gestionhtml InstanceGestion  = new Gestionhtml();
        String LienDesAgents = InstanceGestion.GenererLiensAccueil();
        InstanceGestion.InsertionHTML("StrtoReplace", LienDesAgents);
        InstanceGestion.GenererPageAgent();

    }

}
