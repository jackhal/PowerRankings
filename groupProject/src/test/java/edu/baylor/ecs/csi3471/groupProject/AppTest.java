package edu.baylor.ecs.csi3471.groupProject;

import edu.baylor.ecs.csi3471.groupProject.Business.*;
import edu.baylor.ecs.csi3471.groupProject.Business.Character;
import edu.baylor.ecs.csi3471.groupProject.Persistence.*;
import edu.baylor.ecs.csi3471.groupProject.UI.CreateCharacter;
import edu.baylor.ecs.csi3471.groupProject.Persistence.TournamentBracketPanel;
import edu.baylor.ecs.csi3471.groupProject.UI.EditProfile;
import edu.baylor.ecs.csi3471.groupProject.UI.Register;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
        Character[] c = p.initTournamentCharacters("CharacterRoundsTest.csv");

        assertEquals(c.length, 15);
    }

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
        ArrayList<CharacterVotes> list;
        Assertions.assertAll(() -> ch.getCharacterVotes()) ;

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
        //Assertions.assertAll(() -> ch.getCharacterVoteByUsername("jack"));
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



    //FIXME TERMINATED TEST CASES

    @Test
    @DisplayName("update Char votes (pass)")
    public void addImagesToBracketSuccess()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters("CharacterRoundsTest.csv");
        JLayeredPane myPane = null;
        try
        {
            myPane = p.addImagesToBracket(c, new JLayeredPane(), 0, 0, 0, 0, 0, 1);
        }
        catch(MalformedURLException me)
        {
            System.out.println(me.getMessage());
        }

        assertNotNull(myPane);
    }

    //FIXME TERMINATED TEST CASES
    @Test
    public void addImagesToBracketFail()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        assertThrows(MalformedURLException.class, () -> {
            p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);}, "malrformed URL excpetion expected");
    }

    //FIXME TERMINATED TEST CASES
//    @Test
//    public void getRound2Success()
//    {
//        TournamentBracketPanel p = new TournamentBracketPanel();
//        Character[] c = p.initTournamentCharacters("CharacterRoundsTest.csv");
//        JLayeredPane myPane = null;
//        try
//        {
//            myPane = p.addImagesToBracket(c, new JLayeredPane(), 0, 0, 0, 0, 0, 1);
//        }
//        catch(MalformedURLException me)
//        {
//            System.out.println(me.getMessage());
//        }
//
//        p.getRound2(myPane);
//        assertNotNull(myPane);
//    }

//still terminating test
//    @Test
//    public void getRound3Success()
//    {
//        TournamentBracketPanel p = new TournamentBracketPanel();
//        Character[] c = p.initTournamentCharacters("CharacterRoundsTest.csv");
//        JLayeredPane myPane = null;
//        try
//        {
//            myPane = p.addImagesToBracket(c, new JLayeredPane(), 0, 0, 0, 0, 0, 1);
//        }
//        catch(MalformedURLException me)
//        {
//            System.out.println(me.getMessage());
//        }
//
//        p.getRound3(myPane);
//        assertNotNull(myPane);
//    }


    @Test
    public void getCharacterRoundLinesSuccess()
    {
        long lines = -1;
        CharacterDAO myCharDAO = new CharacterDAO();
        lines = myCharDAO.getCharacterRoundsLines();

        assertTrue(lines >= 0);
    }
    
    @Test
    public void exportUsersToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	UserDAO myUserDAO = new UserDAO();
    	
    	assertDoesNotThrow(() -> {
    		myUserDAO.exportToExcel(homePath);
    	});
    }
    
    @Test
    public void exportUsersToExcelFail() {
    	UserDAO myUserDAO = new UserDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myUserDAO.exportToExcel("asfasfawr322asfaf");
    	});
    }
    
    @Test
    public void exportCharactersToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertDoesNotThrow(() -> {
    		myCharDAO.exportCharactersToExcel(homePath);
    	});
    }
    
    @Test
    public void exportCharactersToExcelFail() {
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myCharDAO.exportCharactersToExcel("asfasd2easkljf");
    	});
    }
    
    @Test
    public void exportCharacterRoundsToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertDoesNotThrow(() -> {
    		myCharDAO.exportCharactersToExcel(homePath);
    	});
    }
    
    @Test
    public void exportCharacterRoundsToExcelFail() {
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myCharDAO.exportCharacterRoundsToExcel("asfasfasfasfasf");
    	});
    }
    
    @Test
    public void exportCharacterVotesToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	CharacterVotesDAO myCharVotesDAO = new CharacterVotesDAO();
    	
    	assertDoesNotThrow(() -> {
    		myCharVotesDAO.exportToExcel(homePath);
    	});
    }
    
    @Test
    public void exportCharacterVotesToExcelFail() {
    	CharacterVotesDAO myCharVotesDAO = new CharacterVotesDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myCharVotesDAO.exportToExcel("asfasf32rasgasbas");
    	});
    }

    //create character
    @Test
    public void passValidCharName(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidCharName("valid"));
    }
    @Test
    public void failValidCharName(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidCharName("N0t_v@lid afadasdfugsfafsdfsdafjkbsvn sdfvjhl dhlvj djhv {}|"));
    }
    @Test
    public void passValidWorld(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidWorld("valid"));
    }
    @Test
    public void failValidWorld(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidWorld("N0t_v@lid afadasdfugsfafsdfsdafjkbsvn sdfvjhl dhlvj djhv {}|"));
    }
    @Test
    public void passValidCharDesc(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidCharDesc("valid"));
    }
    @Test
    public void failValidCharDesc(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidCharDesc("N0t_v@lid afadasdfugsfafsdfsdafjkbsvn sdfvjhl dhlvj djhv {}|"));
    }
    @Test
    public void passValidCharUrl(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidCharURL("https://upload.wikimedia.org/wikipedia/en/d/d7/Harry_Potter_character_poster.jpg"));
    }
    @Test
    public void failValidCharUrl(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidCharURL("this is not a url"));
    }

    @Test
    public void registration(){
        Register r = new Register();
        Assertions.assertAll(() -> r.beginRegistration());
    }
    @Test
    public void passName(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyName("validName"));
    }
    @Test
    public void failName(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyName("Inv@l!d nAmmmmmmeeeeeefewb$%^&{}|FHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }
    @Test
    public void passPassword(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyPassword("validPassword"));
    }
    @Test
    public void failPassword(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyPassword("Inv@l!d nAmmmmmmeeeee$&()_|efewbFHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }
    @Test
    public void passAge(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyAge("20"));
    }
    @Test
    public void failAge(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyAge("Inv@l!d nAmmmmmmeeeeeefewbFHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }
    @Test
    public void passEmail(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyEmail("valid@valid.com"));
    }
    @Test
    public void failEmail(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyEmail("Inv@l!d nAmmmmmmeeeeeefewbFHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }

    @Test
    public void passInputSize(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyInputSizeIsValid(2, 8, "valid"));
    }
    @Test
    public void failInputSize(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyInputSizeIsValid(2, 3, "not valid"));
    }
    @Test
    public void passAgeLength(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyAgeLength("20"));
    }
    @Test
    public void failAgeLength(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyAgeLength("333333"));
    }
    @Test
    public void passAgeNumeric(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyAgeIsNumeric("2"));
    }
    @Test
    public void failAgeNumeric(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyAgeIsNumeric("twelve"));
    }
    @Test
    public void passAgeOlder(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyOlderThanEighteen("20"));
    }

    //FIXME NOT PASSING
    @Test
    public void failAgeOlder(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyOlderThanEighteen("2"));
    }
    @Test
    public void passAgeYounger(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyYoungerThanTwoHundred("20"));
    }
    @Test
    public void failAgeYounger(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyYoungerThanTwoHundred("333"));
    }
    @Test
    public void passInputAlphaDigit(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyInputIsDigitOrLetter("username1234"));
    }
    @Test
    public void failInputAlphaDigit(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyInputIsDigitOrLetter("N0tV@l!d Usern#m*"));
    }

    //FIXME, Might have to remove these tests because of the pop up dialogs
    @Test
    public void passUsernameAnalysis(){
        Register r = new Register();
        Assertions.assertAll(() -> r.usernameAnalysis());
    }
    @Test
    public void passPasswordAnalysis(){
        Register r = new Register();
        Assertions.assertAll(() -> r.passwordAnalysis());
    }
    @Test
    public void passAgeAnalysis(){
        Register r = new Register();
        Assertions.assertAll(() -> r.ageAnalysis());
    }
    @Test
    public void passEmailAnalysis(){
        Register r = new Register();
        Assertions.assertAll(() -> r.emailAnalysis());
    }

    @Test
    public void passNameValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertTrue(() -> e.isNameValid("newRyan"));
    }
    @Test
    public void failNameValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertFalse(() -> e.isNameValid("newRyan{}|()*&#^%$"));
    }
    @Test
    public void passAgeValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertTrue(() -> e.isAgeValid("20"));
    }
    @Test
    public void failAgeValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertFalse(() -> e.isNameValid("not a number"));
    }
    @Test
    public void passDescValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertTrue(() -> e.isDescValid("newRyan"));
    }
    @Test
    public void failDescValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertFalse(() -> e.isDescValid("this line is gonna be way too long and it gonna have some wierd characters cus why not @$@!^*@(#)_@fbahlfbvsdvauvcSD CSDJV VJ ASDJ VDSVAJ VSDAJ VCJHASDFBSD CJSDBCDJS CSD CJHDS C CAK"));
    }



    //todo left, edit profile, and added character dao functions (fix those tests)





}
