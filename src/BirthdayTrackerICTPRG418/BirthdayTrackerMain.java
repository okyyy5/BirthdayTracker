package BirthdayTrackerICTPRG418;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class BirthdayTrackerMain extends Frame implements ActionListener, WindowListener
{
    //Global variables for various methods
    int maxEntries = 50;
    int noOfEntries = 0;
    int currentEntry = 0;


    //Object that stores all the data
    BirthdayTrackerInfo[] BTInfo = new BirthdayTrackerInfo[maxEntries];

    //Various declarations for UI Components and Components that store information
    JButton btnExit, btnNew, btnSave, btnDelete, btnFind, btnFirst, btnLast, btnPrevious, btnNext, btnBinarySearch, btnDisplay, btnShowMonth;
    JLabel lblName, lblSurname, lblMonth, lblDay, lblTitle, lblLikes, lblDislikes, lblDOB;
    JTextField txtName, txtSurname, txtMonth, txtDay, txtFind, txtLikes, txtDislikes;
    JTextArea txtDisplay;
    JComboBox<String> box;


    String[] sortedArray = new String[maxEntries]; //Stores sorted data array for binary search
    String filetosaveto = "MyFriendData.csv";  //Stores file variable where every method is applied to
    String[] comboBoxVars = {"1","2","3","4","5","6","7","8","9","10","11","12"};


    public BirthdayTrackerMain()
    {
        //Sets and formats overall layout
        SpringLayout btlayout = new SpringLayout();
        this.setSize(600,500);
        this.setLayout(btlayout);
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("Birthday Tracker");
        this.setResizable(false);
        this.addWindowListener(this);


        //Sets up various UI Components
        setUpTextFields(btlayout);
        setUpLabels(btlayout);
        setUpButtons(btlayout);
        setUpTextArea(btlayout);
        setUpComboBox(btlayout);
        readFile(filetosaveto);
        viewEntry(currentEntry);
        this.setVisible(true);

    }
    private void setUpComboBox(SpringLayout speclayout){
        box = UIComponents.CreateStringJComboBox(comboBoxVars,300,130,this,this,speclayout);
    }

    //method to setup all the buttons using library
    private void setUpButtons(SpringLayout speclayout){
        btnDelete = UIComponents.CreateJButton("Delete",80,30,490,90,this,this,speclayout);
        btnSave = UIComponents.CreateJButton("Save", 80, 30, 490,60,this,this,speclayout);
        btnExit = UIComponents.CreateJButton("Exit",80,30,490,415,this,this,speclayout);
        btnFind = UIComponents.CreateJButton("Find",80,30,490,180,this,this,speclayout);
        btnBinarySearch = UIComponents.CreateJButton("Search",80, 30, 490,260,this,this,speclayout);
        btnFirst = UIComponents.CreateJButton("|<",50,30,300,30,this,this,speclayout);
        btnLast = UIComponents.CreateJButton(">|",50,30,350,30,this,this,speclayout);
        btnPrevious = UIComponents.CreateJButton("<",50,30,300,90,this,this,speclayout);
        btnNext = UIComponents.CreateJButton(">",50,30,350,90,this,this,speclayout);
        btnNew = UIComponents.CreateJButton("New",80,30,490,30,this,this,speclayout);
        btnDisplay = UIComponents.CreateJButton("Display",80,30,490,150,this,this,speclayout);
        btnShowMonth = UIComponents.CreateJButton("Show Month", 120, 30, 350,130,this,this,speclayout);
    }

    //method to set up all the labels using library
    private void setUpLabels(SpringLayout speclayout){
        lblTitle = UIComponents.CreateJLabel("BIRTHDAY TRACKER", 30,15,this,speclayout);
        lblName = UIComponents.CreateJLabel("Name", 10 , 40, this, speclayout);
        lblSurname = UIComponents.CreateJLabel("Surname", 10, 60, this,speclayout);
        lblDOB = UIComponents.CreateJLabel("Date Of Birth", 10, 100,this,speclayout);
        lblDay = UIComponents.CreateJLabel("Day",100, 80,this,speclayout);
        lblMonth = UIComponents.CreateJLabel("Month",130,80,this,speclayout);
        lblLikes = UIComponents.CreateJLabel("Likes",10,120,this,speclayout);
        lblDislikes = UIComponents.CreateJLabel("Dislikes",10,140,this,speclayout);
    }

    //method to set up all the text fields
    private void setUpTextFields(SpringLayout speclayout){
        txtName = UIComponents.CreateJTextField(20,70,40,this,speclayout);
        txtSurname = UIComponents.CreateJTextField(20,70,60,this,speclayout);
        txtDay = UIComponents.CreateJTextField(2,100,100,this,speclayout);
        txtMonth = UIComponents.CreateJTextField(2,130,100,this,speclayout);
        txtLikes = UIComponents.CreateJTextField(20,70,120,this,speclayout);
        txtDislikes = UIComponents.CreateJTextField(20,70,140,this,speclayout);
        txtFind = UIComponents.CreateJTextField(7,490,220,this,speclayout);
    }

    //method to set up text area
    private void setUpTextArea(SpringLayout speclayout){

        txtDisplay = UIComponents.CreateJTextArea(47,17,10,170,false,this,speclayout);
    }

    //Various button event handlers - when button is clicked what happens
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnDelete){
            //Moves all values in array up one
            for (int i = currentEntry; i < noOfEntries - 1; i++)
            {
                BTInfo[i].setBirthdayTrackerInfo(BTInfo[i+1].getName(), BTInfo[i+1].getSurname(), BTInfo[i+1].getDay(), BTInfo[i+1].getMonth(), BTInfo[i+1].getLikes(), BTInfo[i+1].getDislikes());
            }
            noOfEntries--;
            if (currentEntry > noOfEntries - 1) {
                currentEntry = noOfEntries - 1;
            }
            viewEntry(currentEntry);
        }
        //Saves entries to array
        if(e.getSource() == btnSave){
            saveToArray(currentEntry);
        }
        //writes all of the data in array to file on exit
        if(e.getSource() == btnExit){
            writeToFile(filetosaveto);
            System.exit(0);
        }
        //Finds data by name input
        if(e.getSource() == btnFind){
            boolean found = false;
            int i = 0;
            while (i < noOfEntries && !found)
            {
                if (BTInfo[i].getName().equals( txtFind.getText())) //If search equals inputted text, the intended data point is found
                {
                    found = true;
                }
                i++; //To go through each record one by one
            }
            //Displays entry when found
            if (found) {
                currentEntry = i - 1;
                viewEntry(currentEntry);
            }
        }

        //Sorts data and Conducts a Binary Search on Sorted Data
        if(e.getSource() == btnBinarySearch){
            for(int i = 0; i < noOfEntries; i++)
            {
                sortedArray[i] = BTInfo[i].getName();
            }
            Arrays.sort(sortedArray, 0, noOfEntries);  //Sorts array with parameters (array to store to, which index to sort with, how many/which entries)
            //Displays Sorted Array
            txtDisplay.setText("Birthday Tracker Data Sorted By Name:\n");
            txtDisplay.append("---------------------------------------------------------------------------------------------------------------------\n");
            for(int i = 0; i < noOfEntries; i++)
            {
                txtDisplay.append(sortedArray[i] + "\n");
            }

            //Binary searches sorted list from index 0, inserts that initial entry and displays it in textarea
            int result = Arrays.binarySearch(sortedArray,0, noOfEntries,txtFind.getText());
            txtDisplay.append("---------------------------------------------------------------------------------------------------------------------\n");
            txtDisplay.append("\n Binary Search Result is as follows: " + result);
            txtDisplay.append("\n---------------------------------------------------------------------------------------------------------------------");
        }

        //Displays First Entry in file
        if(e.getSource() == btnFirst){
            try {
                currentEntry = 0;
                viewEntry(currentEntry);
            }catch(Exception error){
                System.err.println("There are no entries: " + error.getMessage());
            }
        }

        //Displays Last Entry in File
        if(e.getSource() == btnLast){
            currentEntry = noOfEntries - 1;
            viewEntry(currentEntry);
        }

        //Displays previous entry in accordance to current visible entry in file
        if(e.getSource() == btnPrevious){
            if(currentEntry > 0){
                currentEntry--;
                viewEntry(currentEntry);
            }
        }

        //Displays next entry in accordance to current visible entry in file
        if(e.getSource() == btnNext){
            if(currentEntry < noOfEntries - 1){
                currentEntry++;
                viewEntry(currentEntry);
            }
        }

        //Empties text fields for a new entry
        if(e.getSource() == btnNew){
            if(noOfEntries < maxEntries - 1){
                noOfEntries++;
                currentEntry = noOfEntries - 1;
                BTInfo[currentEntry] = new BirthdayTrackerInfo("","","","","","");
                viewEntry(currentEntry);
            }
        }

        //Displays data visually in textarea
        if(e.getSource() == btnDisplay) {
            txtDisplay.setText("Name \t Surname \t Month \t Day \t Likes \t Dislikes\n");
            txtDisplay.append("---------------------------------------------------------------------------------------------------------------------\n");
            for (int i = 0; i < noOfEntries; i++) {
                txtDisplay.append(BTInfo[i].getName() + "\t" + BTInfo[i].getSurname() + "\t" + BTInfo[i].getMonth() + "\t" + BTInfo[i].getDay() + "\t" + BTInfo[i].getLikes() + "\t" + BTInfo[i].getDislikes() + "\n");
            }
        }

        //Prints data (if it matches the month in combo box) to text area
        if(e.getSource() == btnShowMonth){
            String chosenMonth = (String)box.getSelectedItem();   //Reads input from combo box
            txtDisplay.setText("People with Birthdays in the Month of: " + chosenMonth + "\n");
            txtDisplay.append("\n");
            txtDisplay.append("Name \t Surname \t Month \t Day \t Likes \t Dislikes\n");
            txtDisplay.append("---------------------------------------------------------------------------------------------------------------------\n");
            for (int i = 0; i < noOfEntries; i++){
                if (Objects.equals(BTInfo[i].getMonth(), chosenMonth)){   //Prints to txtDisplay if it complies with the same month in chosenMonth
                    txtDisplay.append(BTInfo[i].getName() + "\t" + BTInfo[i].getSurname() + "\t" + BTInfo[i].getMonth() + "\t" + BTInfo[i].getDay() + "\t" + BTInfo[i].getLikes() + "\t" + BTInfo[i].getDislikes() + "\n");
                }
            }
        }
    }

    //Various event handlers for the window itself - what happens when the window is interacted with
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}


    //Displays entries in text fields (used for navigation etc.)
    public void viewEntry(int i){
        if(noOfEntries == 0){

        }else{
            txtName.setText(BTInfo[i].getName());
            txtSurname.setText(BTInfo[i].getSurname());
            txtDay.setText(BTInfo[i].getDay());
            txtMonth.setText(BTInfo[i].getMonth());
            txtLikes.setText(BTInfo[i].getLikes());
            txtDislikes.setText(BTInfo[i].getDislikes());
        }

    }

    //Saves data in text fields to object
    public void saveToArray(int i){
        BTInfo[i].setBirthdayTrackerInfo(txtName.getText(),txtSurname.getText(),txtMonth.getText(),txtDay.getText(),txtLikes.getText(),txtDislikes.getText());
        writeToFile(filetosaveto);
    }

    public void readFile(String file){
        try
        {
            FileInputStream fstream = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int i = 0;    //line counter
            String line; //temp line storage

            while ((line = br.readLine()) != null)
            {
                String[] temp = line.split(",");  //Splits data with , when reading each entry
                BTInfo[i] = new BirthdayTrackerInfo(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]); //Organises read data and inserts into temp array

                i++;  //Used as a counter to go through each line
            }
            noOfEntries = i;   // Remembers amount of entries currently in file after reading
            br.close();        // Closes the BufferedReader
            in.close();        // Closes the DataInputStream
            fstream.close();   // Closes the FileInputStream
        } //Exception Handling
        catch (Exception e)
        {
            System.err.println("Failed to Read File: " + e.getMessage());
        }

    }


    public void writeToFile(String file){
        try{
            PrintWriter printFile = new PrintWriter(new FileWriter(file));

            //Handles printing of data in array to file in specified format
            for(int i = 0; i < noOfEntries; i++)
            {
                printFile.println(BTInfo[i].getName() + "," + BTInfo[i].getSurname() + "," + BTInfo[i].getDay() + "," + BTInfo[i].getMonth() + "," + BTInfo[i].getLikes() + "," + BTInfo[i].getDislikes());
            }

            printFile.close(); //Closes print File
        } //Exception Handling
        catch (Exception e)
        {
            System.err.println("Failed to Write To File: " + e.getMessage());
        }
    }
}
