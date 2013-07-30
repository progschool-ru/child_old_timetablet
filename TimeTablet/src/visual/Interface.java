package visual;
import java.awt.*;
import java.awt.event.*;

public class Interface extends Frame
{

    private Button newTaskButton;
    private Label tasksListLabel;

    private int click = 0;

    public Interface()
    {
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        //Просто, чтобы было понятно, что и когда происходит.
        System.out.println("main() started");
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                System.out.println("before View created");
                new Interface().setVisible(true);
                System.out.println("after View created");
            }
        });
        System.out.println("main() finished");
    }


//===========================================================================
    private void initComponents()
    {

        newTaskButton = new Button();
        tasksListLabel = new Label();

        addWindowListener(new MyWindowAdapter());

        newTaskButton.setLabel("Добавить");
        newTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addTask();
            }
        });


        //To, что написано дальше, я нашёл тут: http://www.pawlan.com/monica/articles/awtlayout/
        setLayout(new GridLayout(2, 1));

        add(newTaskButton);
        add(tasksListLabel);
        //Но на самом деле в переспективе нам больше пригодится BoxLayout
        // отсюда: http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
        // и тут: http://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
        //
        // BoxLayout - это первая причина, когда нам понадобился Swing.
        // до этого момента мы прекрасно обходимся чистым awt.



        //Вызываем метод для того, чтобы окно приняло размеры своих детёв
        pack();
        //Но потом делаем его пошире, потому что у нас строки пока не переносятся
        setSize(300, getHeight());
    }

    private void addTask()
    {
        //Сейчас мы просто добавляем к строчке новую строчку.
        //Но они располагаются в одну строчку.
        //
        //Поиск способа располагать строку в контейнере, который понимает "\n" и переносит строки
        //никак не продвигает нас качественно, поэтому так мы не делаем.
        //
        //Правильное решение: располагать каждую задачу в своём собственном элементе.
        //

        click++;
        String newLabelText = tasksListLabel.getText() + "Задача " + click + "\n";
        tasksListLabel.setText(newLabelText);
    }

    private static class MyWindowAdapter extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            //Где ни загуглишь, везде говорят, что в этот момент нужно вызывать System.exit(0)
            //Запоминаем раз и навсегда: System.exit(0) никогда нельзя вызывать.
            //
            //Этот метод я нашёл в документации: http://docs.oracle.com/javase/7/docs/api/java/awt/Window.html
            e.getWindow().dispose();
        }
    }
}