package edu.baylor.ecs.csi3471.groupProject;

import static org.junit.Assert.assertTrue;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.VotingBoothDAO;
import edu.baylor.ecs.csi3471.groupProject.UI.CreateCharacter;
import edu.baylor.ecs.csi3471.groupProject.UI.EditProfile;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void NullCharacter() throws Exception{
        Character A = null;
        Character B = null;
        assertThrows(Exception.class, ()->{VotingBoothDAO votingBoothDAO = new VotingBoothDAO(A, B);});
    }

    @Test
    public void BadUrl() throws Exception{
        Character A = new Character("Bill", "JoeWorld", "Big",5,4,"badurl.com", "Joe");
        Character B = new Character("Joe", "BillWorld", "Small", 10, 10, "badurl.com", "Bill");
        assertThrows(MalformedURLException.class, ()->{VotingBoothDAO boothGUI = new VotingBoothDAO(A,B);});
    }

    @Test
    public void GoodVoting() throws Exception{
        Character A = new Character("Bill", "JoeWorld", "Big",5,4,"https://cdn.britannica.com/41/9641-004-A8DD825D/Yorkshire-boar.jpg", "Joe");
        Character B = new Character("Joe", "BillWorld", "Small", 10, 10, "https://cdn.britannica.com/41/9641-004-A8DD825D/Yorkshire-boar.jpg", "Bill");
        VotingBoothDAO boothGUI = new VotingBoothDAO(A,B);
        assert(boothGUI.isValid());
    }

    @Test
    public void characterCreationBadName() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidCharName("*"));
    }
    
    @Test
    public void characterCreationGoodName() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidCharName("jack"));
    }
    
    @Test
    public void characterCreationBadURL() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidCharURL("google"));
    }
    
    @Test
    public void characterCreationGoodURL() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidCharURL("http://google.com"));
    }
    
    @Test
    public void characterCreationBadWorld() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidWorld("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
    			+ "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"));
    }
    
    @Test
    public void characterCreationGoodWorld() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidWorld("Earth"));
    }
    
    @Test
    public void characterCreationBadDescription() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidCharDesc(""));
    }
    
    @Test
    public void characterCreationGoodDescription() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidCharDesc("description"));
    }
    
    @Test
    public void editProfileBadName() {
    	EditProfile e = new EditProfile("test");
    	assert(!e.isNameValid(null));
    }
    
    @Test
    public void editProfileGoodName() {
    	EditProfile e = new EditProfile("test");
    	assert(e.isNameValid("Jack"));
    }
    
    @Test
    public void editProfileBadAge() {
    	EditProfile e = new EditProfile("test");
    	assert(!e.isAgeValid("201"));
    }
    
    @Test
    public void editProfileGoodAge() {
    	EditProfile e = new EditProfile("test");
    	assert(e.isAgeValid("32"));
    }
    
    @Test
    public void editProfileBadDesc() {
    	EditProfile e = new EditProfile("test");
    	assert(!e.isDescValid(null));
    }
    
    @Test
    public void editProfileGoodDesc() {
    	EditProfile e = new EditProfile("test");
    	assert(e.isDescValid("regular description"));
    }
}
