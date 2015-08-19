//-----------------------------------------------------------------------------
/*
 * This class when called sets a timeer and clears the required label
 * The basic idea for this class came from Stackoverflow
 */
package addressbook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @project Address Book Project
 * @projectVersion 1.1
 * @classVersion 1.0
 * @author Carl Halliburton
 * @parum seconds for timer to wait and JLabel name
 */
//-----------------------------------------------------------------------------
class JlabelClearer {

    private final JLabel label;
    private final int waitSeconds;

    public JlabelClearer(int seconds, JLabel newLabel) {
        label = newLabel;
        waitSeconds = seconds;
    }

    public void startCountdownFromNow() {
        Timer timer = new Timer(waitSeconds * 1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("");
            }
        });
        timer.start();
    }
}
//-----------------------------------------------------------------------------