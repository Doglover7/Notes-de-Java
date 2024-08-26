import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class App {

  static AppNoteServices service;
  static CustomActionListener customActionListener;

  static JTextArea textArea;
  static JComboBox<String> combobox;

  static JFrame frame;
  static JScrollPane scrollList;
  static JPanel panelList;
  static JPanel panelForm;
  
  public static void main(String args[])  {

    App.service = new AppNoteServices();
    App.customActionListener = new CustomActionListener();


    frame = new JFrame();//creating instance of JFrame
    frame.setSize(400,500);//400 width and 500 height
    //frame.setLayout(null);//using no layout managers
    frame.setResizable(false);//do not resize this window
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    JButton btnNewNote =new JButton("New Note");//creating instance of JButton
    JButton btnListNotes =new JButton("List Notes");//creating instance of JButton
    JButton btnSearchNotes =new JButton("Search Notes");//creating instance of JButton
    JButton btnSaveNote =new JButton("Save Note");//creating instance of JButton
    combobox = new JComboBox<String>(new String[]{"No Sort", "Sort Asc", "Sort Desc"});

    JLabel messageLabel = new JLabel("App has started.");
    messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


    panelForm = new JPanel();
    panelForm.setLayout(new BorderLayout());
    panelForm.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    textArea = new JTextArea("Welcome to my java app");
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panelForm.add(scroll, BorderLayout.CENTER);
    panelForm.add(btnSaveNote, BorderLayout.SOUTH);



    panelList = new JPanel();
    panelList.setLayout(new BoxLayout (panelList, BoxLayout.Y_AXIS));
    scrollList = new JScrollPane (panelList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollList.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));




    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);
    toolbar.add(btnNewNote);
    toolbar.add(btnListNotes);
    toolbar.add(btnSearchNotes);
    toolbar.add(combobox);

    frame.getContentPane().add(toolbar, BorderLayout.NORTH);
    frame.getContentPane().add(messageLabel, BorderLayout.SOUTH);
    frame.getContentPane().add(panelForm, BorderLayout.CENTER);

    //listeners
    btnNewNote.addActionListener(App.customActionListener);
    btnListNotes.addActionListener(App.customActionListener);
    btnSearchNotes.addActionListener(App.customActionListener);
    btnSaveNote.addActionListener(App.customActionListener);
    combobox.addActionListener(App.customActionListener);

    frame.setVisible(true);//making the frame visible
  }

  public static void switchComponent(Component component) {
    Container container = App.frame.getContentPane();
    container.remove(App.panelForm);
    container.remove(App.scrollList);

    container.add(component, BorderLayout.CENTER);
    container.revalidate();
    container.repaint();
  }

  public static void refershNoteList(List<String> noteList) {
    noteList = Optional.ofNullable(noteList).orElse( new ArrayList<String>() );
    System.out.println("Total notes:" + noteList.size() );
    App.panelList.removeAll();
    if( noteList.isEmpty() ) {
      JTextArea textArea = new JTextArea("No notes available");
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setEditable( false );
      textArea.setRows(10);
      App.panelList.add( textArea );
    } else {
      for(String note: noteList) {
        JTextArea textArea = new JTextArea(note);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable( false );
        textArea.setRows(10);
        textArea.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        App.panelList.add( textArea );
      }
    }
  }

}

class CustomActionListener implements ActionListener {
  public void actionPerformed(ActionEvent e){
    String command = e.getActionCommand();
    //System.out.println("AppIU:ActionEvent[" + command + "] ");

    if( "New Note".equals(command) ) {
      //App.service.onClickButtonNewNote();
      App.switchComponent(App.panelForm);

    } else if ( "List Notes".equals(command) ) {
      List<String> noteList = App.service.onClickButtonListNotes();
      App.refershNoteList(noteList);
      App.switchComponent(App.scrollList);

    } else if ( "Search Notes".equals(command) ) {
      String keyword = JOptionPane.showInputDialog("Please type your keyword");
      List<String> noteList = App.service.onClickButtonSearchNotes(keyword);
      App.refershNoteList(noteList);
      App.switchComponent(App.scrollList);

    } else if ( "Save Note".equals(command) ) {
      String note = App.textArea.getText();
      List<String> noteList = App.service.onClickButtonSaveNote(note);
      App.refershNoteList(noteList);
      App.switchComponent(App.scrollList);

    } else if ( "comboBoxChanged".equals(command) ) {
      String sortMode = App.combobox.getSelectedItem().toString();
      List<String> noteList = App.service.onClickListSort(sortMode);
      App.refershNoteList(noteList);
      App.switchComponent(App.scrollList);

    } else {
      System.out.println("AppIU:ActionEvent[" + command + "] " + e.getSource().toString());
    }


  }
}