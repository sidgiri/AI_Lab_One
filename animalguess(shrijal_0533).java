import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Appendable;
import java.util.Scanner;

public class AnimalGame
{
    static Node root;                                       // Creates a node to be used throughout as root

    /**
     * No-arg constructor
     */
    public AnimalGame() 
	{
        startNewGame();
    } // end startNewGame

    /**
     * Constructor which accepts a filename from which to
     * rebuild a previously started game.
     * @param filename : name of file to resume game from
     */
    public AnimalGame(String filename)
    {
    } //end AnimalGame constructor

    /**
     * startNewGame()
     * This method builds the initial tree to be used
     * for the duration of the AnimalGame
     */
    public void startNewGame()
    {
        final String ROOT_QUESTION = "Does it have legs";
        final String ANIMAL1 = "Snake";
        final String ANIMAL2 = "Cat";

        root = new Node(ROOT_QUESTION);
        //System.out.println("DEBUG -- " + root.data);
        root.Lchild = new Node(ANIMAL1);
        root.Rchild = new Node(ANIMAL2);

        //return root;
    }  //end startNewGame

    /**
     * resumeGame()
     * This method will resume a game from a previous file, by
     * reading in the tree stored in the @param filename.
     * @param fileName
     * @throws FileNotFoundException
     */
    private void resumeGame(String fileName) throws FileNotFoundException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
    } //end resumeGame

    /**
     * playGame()
     * This is the public accessor method to be called from the client program,
     * calling the objects primary playGame method.
     */
    public void playGame()
    {
        playGame(root);
    } //end playGame

    /**
     * playGame()
     * This is the private playGame method, which takes the
     * root node that belongs to the current object. This method
     * controls one round of gameplay per call.
     * @param current
     */
    private void playGame(Node current)
    {
        while (!isLeaf(current)) {
            // System.out.println("DEBUG -- " + current.data);
            if (askQuestion(current.data)) {
                current = current.Rchild;
            } else {
                current = current.Lchild;
            }
        }

        System.out.print("My guess is " + current.data + ". ");
        if (!askQuestion("Am I right?"))
        {
            insertNewQuestion(current);
        }
        else
        {
            System.out.println("I knew it all along!");
        }
    }  //end playGame

    /**
     * validateInput()
     * Takes the user's input and makes sure it is one of the four
     * acceptable characters: 'N', 'n', 'Y', or 'y', and returns accordingly.
     * @param Input
     * @return
     */
    public static boolean validateInput(char Input)
    {
        if (Input == 'Y' || Input == 'y' || Input == 'N' || Input == 'n')
        {
            return true;
        }
        return false;
    }   // end validateInput

    /**
     * insertNewQuestion()
     * This method is what allows the game to effectively 'learn'
     * upon a failed guess. It will take the animal the user was
     * thinking of, and ask the user to input a distinguishing
     * question for said animal. This method then inputs the
     * new animal and question in their proper spot in the tree,
     * adjusting accordingly.
     * @param current
     */
    public void insertNewQuestion(Node current)
    {
        String guessAnimal;   // The animal that was just guessed
        String correctAnimal; // The animal that the user was thinking of
        String newQuestion;   // A question to distinguish the two animals

        guessAnimal = current.data;
        System.out.println("I give up. What animal are you thinking of: ");
        Scanner input = new Scanner(System.in);
        correctAnimal = input.nextLine();

        System.out.println("Give me a question whose answer is yes for " + correctAnimal + " and no for " + guessAnimal + ": ");

        newQuestion = input.nextLine();

        current.data = newQuestion;
        current.Lchild = new Node(guessAnimal);
        current.Rchild = new Node(correctAnimal);

        //correctAnimal = next input string
        //newquestion = next input sting
    } //end insertNewQuestion

    /**
     * isEmpty()
     * Checks to see if the tree isn't actually a tree yet.
     * @param myNode
     * @return
     */
    public boolean isEmpty(Node myNode)
    {
        if (myNode != null)
        {
            return false;
        }
        return true;
    } //end isEmpty

    /**
     * isLeaf()
     * Checks to see if the supplied node is the bottom of the tree.
     * @param myNode
     * @return
     */
    public boolean isLeaf(Node myNode)
    {
        if (isEmpty(myNode))
        {
            return false;
        }
        if (myNode.Lchild == null && myNode.Rchild == null)
        {
            return true;
        }
        return false;
    } //end isLeaf

    /**
     * saveGame()
     * Writes the current game's tree out to a file for later use
     * @param myNode
     * @param fileName
     * @throws IOException
     */
    public void saveGame(Node myNode, String fileName) throws IOException
    {
   
        
        
    } // end saveGame

    /**
     * askQuestion()
     * This method deals with user input; presenting the desired
     * prompt for the current stage of the game. It then validates the
     * user's input, prompting them to respond properly if necessary.
     * @param Question
     * @return
     */
    public static boolean askQuestion(String Question)
    {
        String prompt = Question;

        System.out.print(prompt + " [Y or N]: ");
        Scanner in = new Scanner(System.in);

        String Input = in.nextLine();
        char UserAnswer = Input.charAt(0);

        while (!validateInput(UserAnswer))
        {
            System.out.print("Invalid response. Please type Y or N: ");
            Input = in.nextLine();
            UserAnswer = Input.charAt(0);
        }

        return (UserAnswer == 'Y' || UserAnswer == 'y');
    } // end askQuestion
}  // end class declaration