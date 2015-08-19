//-----------------------------------------------------------------------------
/*Gui form for the addressbook
 * contains:
 * frames(1), Panels(4) tabbedPane(2), buttons(7), fields(12), combobox(2)
 * Menus(1), MenuItems(6), FileChoosers(2), JOptionPane(4)
 */
package addressbook;

import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @project Address Book Project
 * @projectVersion 1.1
 * @classVersion 1.1
 * @author Carl Halliburton
 */
//-----------------------------------------------------------------------------
public class AddressBookFrame extends javax.swing.JFrame {
    
    private final AddressBook aBook;
    private final XmlValidate validate;
    private int index;
    
    /**
     * Creates new form AddressBookFrame
     */
    public AddressBookFrame() {
        initComponents();  
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        aBook = new AddressBook();   
        validate = new XmlValidate();
        aBook.openContactList();   
        setCbxContactList();       
        cbxCountryList.setSelectedItem(null);
        cbxContactList.setSelectedItem(null);
        disableButtons();
    }
    //Clears the textfields and cbxCountryList Combobox
    public void clearInput() {
        fieldFname.setText("");
        fieldMname.setText("");
        fieldLname.setText("");
        fieldEmail.setText("");
        fieldMobile.setText("");
        fieldHome.setText("");
        fieldStreet.setText("");
        fieldSuburb.setText("");
        fieldCity.setText("");
        fieldRegion.setText("");
        fieldPostCode.setText("");
        cbxCountryList.setSelectedIndex(-1);
    }
    //Clears the displayArea and cbxContactList combobox
    public void refreshContactDisplay() {
        displayArea.setText("");
        cbxContactList.setSelectedIndex(-1);
    }
    
    //fills cbxContactList combobox with the lastanme and firstname 
    //of each contact 
    public void setCbxContactList() {
        String contactName;
        cbxContactList.removeAllItems();
        if (aBook.isListEmpty() == false) {
            index = aBook.getContactListSize();

            for (int i = 0; i < index; i++) {
                contactName = aBook.updateCbxContactList(i);
                cbxContactList.addItem(contactName);
            }
            cbxContactList.setSelectedIndex(-1);
        }
    }

    public void printContact() {
        Contact curContact;
        curContact = aBook.getContact(index);
        
        displayArea.append("Name: ");
        displayArea.append(curContact.getFirstName());
        displayArea.append(" ");
        displayArea.append(curContact.getMiddleName());
        displayArea.append(" ");
        displayArea.append(curContact.getLastName());
        displayArea.append("\n\nEmail: ");
        displayArea.append(curContact.getEmail());
        displayArea.append("\nMobile: ");
        displayArea.append(curContact.getMobilePhone());
        displayArea.append("\nHome: ");
        displayArea.append(curContact.getHomePhone());
        displayArea.append("\n\nAddress \n");
        displayArea.append(curContact.getStreet());
        displayArea.append("\n");
        displayArea.append(curContact.getSuburb());
        displayArea.append(" ");
        displayArea.append(curContact.getCity());
        displayArea.append("\n");
        if (curContact.getRegion().equals("") != true) {
            displayArea.append(curContact.getRegion());
            displayArea.append(", ");
        }
        displayArea.append(curContact.getCountry());
        displayArea.append("\n");
        displayArea.append(curContact.getPostCode());
    }
    
    //fills the contact imput fields with the contact to be updated
    public void updateContact() {
        btnSubmit.setEnabled(false);
        btnClearAll.setEnabled(false);
        btnSubmitUpdate.setEnabled(true);
        btnCancel.setEnabled(true);
        tabNewViewContact.setSelectedIndex(0);
        tabContactDetials.setSelectedIndex(0);
        tabNewViewContact.setEnabledAt(1, false);
        tabNewViewContact.setTitleAt(0, "Edit Contact");
        
        Contact curContact;
        curContact = aBook.getContact(index);
        
        //fill textfields ready to updated
        fieldFname.setText(curContact.getFirstName());
        fieldMname.setText(curContact.getMiddleName());
        fieldLname.setText(curContact.getLastName());
        fieldEmail.setText(curContact.getEmail());
        fieldMobile.setText(curContact.getMobilePhone());
        fieldHome.setText(curContact.getHomePhone());
        
        fieldStreet.setText(curContact.getStreet());
        fieldSuburb.setText(curContact.getSuburb());
        fieldCity.setText(curContact.getCity());
        fieldRegion.setText(curContact.getRegion());
        cbxCountryList.setSelectedItem(curContact.getCountry());
        fieldPostCode.setText(curContact.getPostCode());
    }
    
    public void disableButtons() {
        btnSubmit.setEnabled(false);
        btnClearAll.setEnabled(false);
        btnCancel.setEnabled(false);
        btnSubmitUpdate.setEnabled(false);
    }
    
    //checks whether a contact was selected
    public void displayContact() {   
        if (cbxContactList.getSelectedIndex() != -1) {
            displayArea.setText("");
            index = cbxContactList.getSelectedIndex();
            printContact(); 
        }
    }
    
    public void confirmCloseAction() {
          int selectedOption = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you wish to exit", "Address book", 
                    JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
           aBook.saveContactList();
           System.exit(0);
        }
    }
    
    //checks whether the user wishes to 
    //append the imported contact or overwrite the contactList
    public boolean importOption() {
        boolean importOpStat;
        ButtonGroup group = new ButtonGroup();
        JRadioButton rbAppend = new JRadioButton("Append to ContactList");
        JRadioButton rbOverWrite = new JRadioButton("Overwrite ContactList");
        rbAppend.setSelected(true);
        group.add(rbAppend);
        group.add(rbOverWrite);  
        Object[] dialogArray = {new JLabel("Select an option"), 
                                                        rbAppend, rbOverWrite};
    
        JOptionPane.showConfirmDialog(null, dialogArray, "Select", 
                                    JOptionPane.OK_CANCEL_OPTION);
       
        if (rbAppend.isSelected())
            importOpStat = true; //appending
        else
            importOpStat = false; //overwrite
        
        return importOpStat;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jFrame1 = new javax.swing.JFrame();
        fileChooserImport = new javax.swing.JFileChooser();
        jFileChooser1 = new javax.swing.JFileChooser();
        tabNewViewContact = new javax.swing.JTabbedPane();
        tabContactDetials = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        labelFn = new javax.swing.JLabel();
        labelLn = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelMobile = new javax.swing.JLabel();
        labelMn = new javax.swing.JLabel();
        fieldMname = new javax.swing.JTextField();
        fieldLname = new javax.swing.JTextField();
        fieldEmail = new javax.swing.JTextField();
        fieldMobile = new javax.swing.JTextField();
        labelHome = new javax.swing.JLabel();
        fieldHome = new javax.swing.JTextField();
        fieldFname = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        labelStreet = new javax.swing.JLabel();
        labelSuburb = new javax.swing.JLabel();
        labelCity = new javax.swing.JLabel();
        labelRegion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelPostCode = new javax.swing.JLabel();
        fieldStreet = new javax.swing.JTextField();
        fieldSuburb = new javax.swing.JTextField();
        fieldCity = new javax.swing.JTextField();
        fieldRegion = new javax.swing.JTextField();
        fieldPostCode = new javax.swing.JTextField();
        cbxCountryList = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        labelSelect = new javax.swing.JLabel();
        cbxContactList = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        btnDisplay = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnSubmitUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemNewContact = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemExport = new javax.swing.JMenuItem();
        menuItemImport = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabNewViewContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabNewViewContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabNewViewContactFocusLost(evt);
            }
        });

        tabContactDetials.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabContactDetialsFocusGained(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        labelFn.setText("First Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(64, 18, 0, 0);
        jPanel1.add(labelFn, gridBagConstraints);

        labelLn.setText("Last Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 18, 0, 0);
        jPanel1.add(labelLn, gridBagConstraints);

        labelEmail.setText("Email Address");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 18, 0, 0);
        jPanel1.add(labelEmail, gridBagConstraints);

        labelMobile.setText("Mobile");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 18, 0, 0);
        jPanel1.add(labelMobile, gridBagConstraints);

        labelMn.setText("Middle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(64, 20, 0, 0);
        jPanel1.add(labelMn, gridBagConstraints);

        fieldMname.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 64;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(61, 20, 0, 18);
        jPanel1.add(fieldMname, gridBagConstraints);

        fieldLname.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel1.add(fieldLname, gridBagConstraints);

        fieldEmail.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel1.add(fieldEmail, gridBagConstraints);

        fieldMobile.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel1.add(fieldMobile, gridBagConstraints);

        labelHome.setText("Home");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 18, 0, 0);
        jPanel1.add(labelHome, gridBagConstraints);

        fieldHome.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 61, 0);
        jPanel1.add(fieldHome, gridBagConstraints);

        fieldFname.setColumns(15);
        fieldFname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldFnameFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(61, 20, 0, 0);
        jPanel1.add(fieldFname, gridBagConstraints);
        jPanel1.add(jSeparator1, new java.awt.GridBagConstraints());

        tabContactDetials.addTab("Contact", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelStreet.setText("Street");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(48, 27, 0, 0);
        jPanel2.add(labelStreet, gridBagConstraints);

        labelSuburb.setText("Suburb");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 27, 0, 0);
        jPanel2.add(labelSuburb, gridBagConstraints);

        labelCity.setText("City");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 27, 0, 0);
        jPanel2.add(labelCity, gridBagConstraints);

        labelRegion.setText("Region/State");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 29, 0, 0);
        jPanel2.add(labelRegion, gridBagConstraints);

        jLabel5.setText("Country");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 27, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        labelPostCode.setText("Post/Zip Code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 27, 0, 0);
        jPanel2.add(labelPostCode, gridBagConstraints);

        fieldStreet.setColumns(30);
        fieldStreet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldStreetFocusGained(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(45, 20, 0, 68);
        jPanel2.add(fieldStreet, gridBagConstraints);

        fieldSuburb.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel2.add(fieldSuburb, gridBagConstraints);

        fieldCity.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel2.add(fieldCity, gridBagConstraints);

        fieldRegion.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel2.add(fieldRegion, gridBagConstraints);

        fieldPostCode.setColumns(16);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 128;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 46, 0);
        jPanel2.add(fieldPostCode, gridBagConstraints);

        cbxCountryList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe" }));
        cbxCountryList.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel2.add(cbxCountryList, gridBagConstraints);

        tabContactDetials.addTab("Address", jPanel2);

        tabNewViewContact.addTab("New Contact", tabContactDetials);

        labelSelect.setText("Select Contact");

        cbxContactList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxContactListActionPerformed(evt);
            }
        });

        displayArea.setEditable(false);
        displayArea.setColumns(25);
        displayArea.setRows(5);
        displayArea.setTabSize(10);
        jScrollPane2.setViewportView(displayArea);

        btnDisplay.setText("Display");
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });
        btnDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDisplayKeyPressed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(labelSelect)
                        .addGap(10, 10, 10)
                        .addComponent(cbxContactList, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(labelSelect))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cbxContactList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnDisplay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)))
                .addContainerGap())
        );

        tabNewViewContact.addTab("View Contact", jPanel3);

        tabNewViewContact.setSelectedIndex(1);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnClearAll.setText("Clear All");
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        btnSubmitUpdate.setText("Update");
        btnSubmitUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitUpdateActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        menuItemNewContact.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        menuItemNewContact.setText("New Contact");
        menuItemNewContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNewContactActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemNewContact);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("View Contact");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenu2.setText("Export/Import");

        menuItemExport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        menuItemExport.setText("Export as XML");
        menuItemExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExportActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemExport);

        menuItemImport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        menuItemImport.setText("Import from XML");
        menuItemImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemImportActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemImport);

        jMenu1.add(jMenu2);

        menuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuItemExit.setText("Close");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Help");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSubmit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClearAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSubmitUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabNewViewContact, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabNewViewContact, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(labelStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClearAll)
                    .addComponent(btnSubmit)
                    .addComponent(btnSubmitUpdate)
                    .addComponent(btnCancel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        //submits a contact and places it in the contactList
        String country = "";
        
        if (cbxCountryList.getSelectedIndex() != -1)
            country = cbxCountryList.getSelectedItem().toString(); 

        aBook.setContact(fieldFname.getText(), fieldMname.getText(),
                         fieldLname.getText(), fieldEmail.getText(), 
                         fieldMobile.getText(), fieldHome.getText(), 
                         fieldStreet.getText(), fieldSuburb.getText(), 
                         fieldCity.getText(), fieldRegion.getText(),
                         country, fieldPostCode.getText() );
        clearInput();
        setCbxContactList();
        aBook.saveContactList();
        labelStatus.setText("Contact added succesfully");
        new JlabelClearer(3, labelStatus).startCountdownFromNow();   
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        clearInput();
    }//GEN-LAST:event_btnClearAllActionPerformed

    private void cbxContactListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxContactListActionPerformed
       btnDisplay.requestFocus();
    }//GEN-LAST:event_cbxContactListActionPerformed

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        displayContact();
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        refreshContactDisplay();
    }//GEN-LAST:event_btnClearActionPerformed

    private void fieldFnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldFnameFocusGained
        if (tabNewViewContact.getTitleAt(0).equals("New Contact")) {
            btnSubmit.setEnabled(true);
            btnClearAll.setEnabled(true);
        }        
        else {
            btnSubmitUpdate.setEnabled(true);
            btnCancel.setEnabled(true);
        }

    }//GEN-LAST:event_fieldFnameFocusGained

    private void tabNewViewContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabNewViewContactFocusGained
        disableButtons();
    }//GEN-LAST:event_tabNewViewContactFocusGained

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (cbxContactList.getSelectedIndex() != -1) {
            index = cbxContactList.getSelectedIndex();
            refreshContactDisplay();
            displayArea.setText("");
            updateContact();
        }   
    }//GEN-LAST:event_btnEditActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        confirmCloseAction();
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemNewContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNewContactActionPerformed
        tabNewViewContact.setSelectedIndex(0);
    }//GEN-LAST:event_menuItemNewContactActionPerformed

    private void fieldStreetFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldStreetFocusGained
        if (tabNewViewContact.getTitleAt(0).equals("New Contact")) {
            btnSubmit.setEnabled(true);
            btnClearAll.setEnabled(true);
        }
        else {
            btnSubmitUpdate.setEnabled(true);
            btnCancel.setEnabled(true);
        }
    }//GEN-LAST:event_fieldStreetFocusGained

    private void btnSubmitUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitUpdateActionPerformed
        //set contact object with new values
        String country = "";
        Contact curContact;
        curContact = aBook.getContact(index);
        
        if (cbxCountryList.getSelectedIndex() != -1)
            country = cbxCountryList.getSelectedItem().toString();
        
        curContact.setFirstName(fieldFname.getText());
        curContact.setMiddleName(fieldMname.getText());
        curContact.setLastName(fieldLname.getText());
        curContact.setEmail(fieldEmail.getText());
        curContact.setMobilePhone(fieldMobile.getText());
        curContact.setHomePhone(fieldHome.getText());
        
        curContact.setStreet(fieldStreet.getText());
        curContact.setSuburb(fieldSuburb.getText());
        curContact.setCity(fieldCity.getText());
        curContact.setRegion(fieldRegion.getText());
        curContact.setCountry(country);
        curContact.setPostCode(fieldPostCode.getText());
        
        clearInput();
       //update element in combobox
        setCbxContactList();
        refreshContactDisplay();
        labelStatus.setText("Contact updated succesfully");
        new JlabelClearer(5, labelStatus).startCountdownFromNow();
        
        tabNewViewContact.setTitleAt(0, "New Contact");
        tabNewViewContact.setEnabledAt(1, true);
        tabNewViewContact.setSelectedIndex(1);
        disableButtons();
    }//GEN-LAST:event_btnSubmitUpdateActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        tabNewViewContact.setTitleAt(0, "New Contact");
        tabNewViewContact.setEnabledAt(1, true);
        tabNewViewContact.setSelectedIndex(1);
        disableButtons();
        clearInput();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnDisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDisplayKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER)
            displayContact();
    }//GEN-LAST:event_btnDisplayKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (cbxContactList.getSelectedIndex() != -1) {
            index = cbxContactList.getSelectedIndex();
            int selectedOption = JOptionPane.showConfirmDialog(null, 
                    "Are you sure you wish to delete contact", "choose one", 
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                aBook.deleteContact(index);
                setCbxContactList();
                refreshContactDisplay();
            }
        } 
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void menuItemExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExportActionPerformed
        String xmlFilePath;
        int returnVal;
                                    //check if anything to export
        if (aBook.isListEmpty() != true) { 
            aBook.exportAsXml();
            org.jdom2.Document doc = aBook.getDocument();
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("contactList.xml"));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            returnVal = chooser.showSaveDialog(this);   //check if ok selected
            if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
                try {
                    xmlFilePath = chooser.getSelectedFile().getAbsolutePath();
                    XMLOutputter xmlOutputter = 
                                    new XMLOutputter(Format.getPrettyFormat());
                    xmlOutputter.output(doc, new FileOutputStream(xmlFilePath)); 
                    JOptionPane.showMessageDialog(null, "Export Successful");
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No Contacts Avalieble");
        
    }//GEN-LAST:event_menuItemExportActionPerformed

    private void tabContactDetialsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabContactDetialsFocusGained
        if (tabNewViewContact.getTitleAt(0).equals("New Contact")) {
            btnSubmit.setEnabled(true);
            btnClearAll.setEnabled(true);
        }        
        else {
            btnSubmitUpdate.setEnabled(true);
            btnCancel.setEnabled(true);
        }
    }//GEN-LAST:event_tabContactDetialsFocusGained

    private void menuItemImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemImportActionPerformed
        String xmlPath;
        int returnVal; 
        String xsdPath = "C:\\Users\\User\\Documents\\NetBeansProjects\\AddressBook\\contactList.xsd";
        boolean importOpStat;
        boolean xmlIsValid;
                                            
        returnVal = fileChooserImport.showOpenDialog( this );
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            importOpStat = importOption();
            java.io.File file = fileChooserImport.getSelectedFile( );
            xmlPath = file.toString();
            xmlIsValid = XmlValidate.validateXMLSchema(xsdPath, xmlPath);
            if (xmlIsValid == false)
                JOptionPane.showMessageDialog(null, "Xml Document Invalid");
            else {
                aBook.importFromXml(xmlPath, importOpStat);
                setCbxContactList();
            }
        }
    }//GEN-LAST:event_menuItemImportActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmCloseAction();
    }//GEN-LAST:event_formWindowClosing

    private void tabNewViewContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabNewViewContactFocusLost
        refreshContactDisplay();
    }//GEN-LAST:event_tabNewViewContactFocusLost

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        tabNewViewContact.setSelectedIndex(1);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "AddressBook Project" +
                                            "\nVersion: 1.0" +
                                            "\nYear: 2015" +
                                            "\nLanguage: Java" +
                                            "\nAuther: Carl Halliburton");
    }//GEN-LAST:event_jMenuItem1ActionPerformed
       
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddressBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddressBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddressBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddressBookFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddressBookFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSubmitUpdate;
    private javax.swing.JComboBox cbxContactList;
    private javax.swing.JComboBox cbxCountryList;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JTextField fieldCity;
    private javax.swing.JTextField fieldEmail;
    private javax.swing.JTextField fieldFname;
    private javax.swing.JTextField fieldHome;
    private javax.swing.JTextField fieldLname;
    private javax.swing.JTextField fieldMname;
    private javax.swing.JTextField fieldMobile;
    private javax.swing.JTextField fieldPostCode;
    private javax.swing.JTextField fieldRegion;
    private javax.swing.JTextField fieldStreet;
    private javax.swing.JTextField fieldSuburb;
    private javax.swing.JFileChooser fileChooserImport;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelCity;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelFn;
    private javax.swing.JLabel labelHome;
    private javax.swing.JLabel labelLn;
    private javax.swing.JLabel labelMn;
    private javax.swing.JLabel labelMobile;
    private javax.swing.JLabel labelPostCode;
    private javax.swing.JLabel labelRegion;
    private javax.swing.JLabel labelSelect;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelStreet;
    private javax.swing.JLabel labelSuburb;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemExport;
    private javax.swing.JMenuItem menuItemImport;
    private javax.swing.JMenuItem menuItemNewContact;
    private javax.swing.JTabbedPane tabContactDetials;
    private javax.swing.JTabbedPane tabNewViewContact;
    // End of variables declaration//GEN-END:variables
}
//-----------------------------------------------------------------------------