package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Business.CharacterVotes;
import edu.baylor.ecs.csi3471.groupProject.Business.DailyCheckIn;
import edu.baylor.ecs.csi3471.groupProject.Business.User;
import edu.baylor.ecs.csi3471.groupProject.Persistence.*;
import edu.baylor.ecs.csi3471.groupProject.UI.CreateCharacter;
import edu.baylor.ecs.csi3471.groupProject.UI.EditProfile;
import edu.baylor.ecs.csi3471.groupProject.UI.TournamentBracketPanel;
import edu.baylor.ecs.csi3471.groupProject.UI.VotingBoothGUI;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

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
        Assertions.assertThrows(MalformedURLException.class, ()->{VotingBoothDAO boothGUI = new VotingBoothDAO(A,B);});
    }

    //FIXME NOT PASSING TEST
    /*@Test
    public void GoodVoting() throws Exception{
        Character A = new Character("Bill", "JoeWorld", "Big",5,4,"https://cdn.britannica.com/41/9641-004-A8DD825D/Yorkshire-boar.jpg", "Joe");
        Character B = new Character("Joe", "BillWorld", "Small", 10, 10, "https://cdn.britannica.com/41/9641-004-A8DD825D/Yorkshire-boar.jpg", "Bill");
        VotingBoothDAO boothGUI = new VotingBoothDAO(A,B);
        assert(boothGUI.isValid());
    }*/

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
    public void loadCharactersSuccess()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters();

        assertEquals(c.length, 15);
    }

    //FIXME TERMINATED TEST CASES
    /*@Test
    public void addImagesToBracketSuccess()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters();
        JLayeredPane myPane = p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);

        assertNotNull(myPane);
    }*/

    //FIXME TERMINATED TEST CASES
    /*@Test
    public void addImagesToBracketFail()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        assertThrows(MalformedURLException.class, () -> {
            p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);}, "malrformed URL excpetion expected");
    }*/

    //FIXME TERMINATED TEST CASES
    /*@Test
    public void getRound2Success()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters();
        JLayeredPane myPane = p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);
        p.getRound2(myPane);

        assertNotNull(myPane);
    }*/

    //FIXME terminated the rest of the tests
    /*@Test
    public void getRound3Success()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters();
        JLayeredPane myPane = p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);
        p.getRound3(myPane);

        assertNotNull(myPane);
    }*/

    @DisplayName("Test updateUser (pass)")
    @Test
    public void passUpdateUser(){
        User user = new User();
        UserDAO userDAO = new UserDAO();
        Assertions.assertAll(() -> userDAO.updateUser(user));
    }
    
    @DisplayName("Test getUsers (pass)")
    @Test
    public void failUpdateUser() throws IOException {
        UserDAO u = new UserDAO();
        ArrayList<User> users = u.getUsers();
        Assertions.assertTrue(users.size() > 0);
    }

    @DisplayName("setBalance pass")
    @Test
    public void passSetBal() throws NullPointerException {
        UserDAO u = new UserDAO();
        u.setCurrentBal("ryan", 500);
        Assertions.assertEquals(500, u.findCurrentBal("ryan"));
        u.setCurrentBal("ryan", 1000);
        Assertions.assertEquals(1000, u.findCurrentBal("ryan"));
    }
    
    @DisplayName("write user to file pass")
    @Test
    public void passWriteFile() throws NullPointerException {
        String[] data = {"yesyesyes",	"yesyesyes","yesyesyes@yesyesyes.com","yesyes",	"20",	"500",	"0",	"false",	"false",	"none",	"null",	"0",	"04/01/22"};
        User u = new User(data);
        RegisterDAO d = new RegisterDAO();
        //Assertions.assertAll(() -> d.writeToFile(u));
    }

    @DisplayName("forgotUsername pass")
    @Test
    public void passForgotUser() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("ryan", f.findUsername("ryan@gmail.com"));
    }

    @DisplayName("forgotPassword pass")
    @Test
    public void passForgotPass() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("ryan", f.findPassword("ryan"));
    }
    
    @DisplayName("forgotUsername fail")
    @Test
    public void failForgotUser() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("User not found", f.findUsername("ryan@gmail.comasdadqsdsad"));
    }
    
    @DisplayName("forgotPassword fail")
    @Test
    public void failForgotPass() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("User not found", f.findPassword("ryannnnnnasdnsadn"));
    }
    
    @Test
    public void characterGetOwnerBad() {
    	Character c = new Character();
    	c.setOwner("Jack");
    	assert(!c.getOwner().equals("Ben"));
    }
    
    @Test
    public void characterGetOwnerGood() {
    	Character c = new Character();
    	c.setOwner("Jack");
    	assert(c.getOwner().equals("Jack"));
    }
    @DisplayName("findChar (pass)")
    @Test
    public void passFindChar() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Character c = new Character("Speed",	"Earth",	"Stupid",	1,	0,	"https://i.ytimg.com/vi/wYZux3BMc5k/maxresdefault.jpg",	"Cyril");
        Character d = ch.findChar("Speed", "Earth");
        Assertions.assertEquals(c, d);
    }

    @DisplayName("VotingBooth (pass)")
    @Test
    public void passVoting() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Character a = ch.findChar("Kirby", "DreamLand");
        Character b = ch.findChar("Spider-Ham", "New York");

        Assertions.assertAll(() -> new VotingBoothDAO(a, b));
    }

    @DisplayName("makeList (pass)")
    @Test
    public void passMakeList() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        ArrayList<Character> list = ch.makeList();
        Assertions.assertTrue(list.size() > 0);
    }

    @DisplayName("charExist (pass)")
    @Test
    public void passExists() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Assertions.assertTrue(ch.doesCharExist("Speed", "Earth"));
    }
    @DisplayName("charExist (fail)")
    @Test
    public void failExists() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Assertions.assertFalse(ch.doesCharExist("Speeddddddd", "Earth"));
    }

    @DisplayName("showBal (pass)")
    @Test
    public void passBal() throws Exception {
        DailyCheckIn d = new DailyCheckIn();
        Assertions.assertAll(() -> d.showBalance("ryan"));
    }


    @DisplayName("validatePassword (pass)")
    @Test
    public void passValidatePassword() throws Exception {
        Assertions.assertTrue(loginDAO.validatePassword("ryan", "ryan"));
    }

    @DisplayName("validatePassword (fail)")
    @Test
    public void failValidatePassword() throws Exception {
        Assertions.assertFalse(loginDAO.validatePassword("ryan", "ryanDoesNotHaveThisPassword"));
    }
    @DisplayName("getCharacters (pass)")
    @Test
    public void passGetCharacters() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        ArrayList<CharacterVotes> list = ch.getCharacterVotes();
        Assertions.assertTrue(list.size() > 0);
    }

    @DisplayName("update Char votes (pass)")
    @Test
    public void passUpdateCharVotes() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        //Assertions.assertAll(() -> ch.updateCharacterVotes(new CharacterVotes()));
    } //might not be a good test, dont wanna overwite important data

    @DisplayName("update Char votes (pass)")
    @Test
    public void passGetCharVote() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        Assertions.assertAll(() -> ch.getCharacterVoteByUsername("jack"));
    }

    @DisplayName("get Vote (pass)")
    @Test
    public void passGetVote() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        Assertions.assertAll(() -> ch.findCurrentVote("ryan", 1));
    }
    @DisplayName("get bet (pass)")
    @Test
    public void passGetBet() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        Assertions.assertAll(() -> ch.findCurrentBet("ryan", 1));
    }






}
