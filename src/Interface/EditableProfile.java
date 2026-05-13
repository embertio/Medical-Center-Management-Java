/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;


public interface EditableProfile {
    String getId();//setting what method needs to be used
    String getName();
    String getContact();
    String getIc();
    String getGender();
    java.time.LocalDate getDob();
    void setName(String name);
    void setContact(String contact);
    String getRole(); 
}
