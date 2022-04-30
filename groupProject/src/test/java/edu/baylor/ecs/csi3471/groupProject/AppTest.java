package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.VotingBoothDAO;
import edu.baylor.ecs.csi3471.groupProject.UI.CreateCharacter;
import edu.baylor.ecs.csi3471.groupProject.UI.EditProfile;
import edu.baylor.ecs.csi3471.groupProject.UI.TournamentBracketPanel;
import edu.baylor.ecs.csi3471.groupProject.UI.VotingBoothGUI;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.*;
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

    @Test
    public void loadCharactersSuccess()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters();

        assertEquals(c.length, 15);
    }

    @Test
    public void addImagesToBracketSuccess()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters();
        JLayeredPane myPane = p.addImagesToBracket(c, new JLayeredPane(), 0, 0, 0, 0, 0, 1);

        assertNotNull(myPane);
    }

//    @Test
//    public void addImagesToBracketFail()
//    {
//        TournamentBracketPanel p = new TournamentBracketPanel();
//        assertThrows(MalformedURLException.class, () -> {
//            p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);}, "malrformed URL excpetion expected");
//    }
//
//    @Test
//    public void getRound2Success()
//    {
//        TournamentBracketPanel p = new TournamentBracketPanel();
//        Character[] c = p.initTournamentCharacters();
//        JLayeredPane myPane = p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);
//        p.getRound2(myPane);
//
//        assertNotNull(myPane);
//    }
//
//    @Test
//    public void getRound3Success()
//    {
//        TournamentBracketPanel p = new TournamentBracketPanel();
//        Character[] c = p.initTournamentCharacters();
//        JLayeredPane myPane = p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);
//        p.getRound3(myPane);
//
//        assertNotNull(myPane);
//    }




}
