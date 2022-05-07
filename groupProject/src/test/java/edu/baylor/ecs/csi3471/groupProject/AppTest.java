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
    /**
     * Abstraction for a passing test case
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    
    /** 
     * Tests null exception is thrown on VotingBootDAO init
     * @throws Exception
     */
    @Test
    public void NullCharacter() throws Exception{
        Character A = null;
        Character B = null;
        assertThrows(Exception.class, ()->{VotingBoothDAO votingBoothDAO = new VotingBoothDAO(A, B);});
    }

    
    /** 
     * Tests exception is thrown on invalid url
     * @throws Exception
     */
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

    /**
     * Tests Character Name input validation
     */
    @Test
    public void characterCreationBadName() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidCharName("*"));
    }
    
    /**
     * Tests Character name input validation
     */
    @Test
    public void characterCreationGoodName() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidCharName("jack"));
    }
    
    /**
     * Tests url validation
     */
    @Test
    public void characterCreationBadURL() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidCharURL("google"));
    }
    
    /**
     * Tests url validation
     */
    @Test
    public void characterCreationGoodURL() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidCharURL("http://google.com"));
    }
    
    /**
     * Tests world validation
     */
    @Test
    public void characterCreationBadWorld() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidWorld("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
    			+ "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"));
    }
    
    /**
     * Tests world validation
     */
    @Test
    public void characterCreationGoodWorld() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidWorld("Earth"));
    }
    
    /**
     * Tests description validation
     */
    @Test
    public void characterCreationBadDescription() {
    	CreateCharacter c = new CreateCharacter();
    	assert(!c.isValidCharDesc(""));
    }
    
    /**
     * Tests description validation
     */
    @Test
    public void characterCreationGoodDescription() {
    	CreateCharacter c = new CreateCharacter();
    	assert(c.isValidCharDesc("description"));
    }

    /**
     * Tests initTournamentCharacters() correctly initializes array
     */
    @Test
    public void loadCharactersSuccess()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        Character[] c = p.initTournamentCharacters("CharacterRoundsTest.csv");

        assertEquals(c.length, 15);
    }

    /**
     * Tests updateUser() method with valid user
     */
    @DisplayName("Test updateUser (pass)")
    @Test
    public void passUpdateUser(){
        User user = new User();
        UserDAO userDAO = new UserDAO();
        Assertions.assertAll(() -> userDAO.updateUser(user));
    }
    
    
    /** 
     * Tests getUsers() method assuming length of users is greater than 0
     * @throws IOException
     */
    @DisplayName("Test getUsers (pass)")
    @Test
    public void failUpdateUser() throws IOException {
        UserDAO u = new UserDAO();
        ArrayList<User> users = u.getUsers();
        Assertions.assertTrue(users.size() > 0);
    }

    
    /** 
     * Tests setCurrentBal method with valid username and balance
     * @throws NullPointerException
     */
    @DisplayName("setBalance pass")
    @Test
    public void passSetBal() throws NullPointerException {
        UserDAO u = new UserDAO();
        u.setCurrentBal("ryan", 500);
        Assertions.assertEquals(500, u.findCurrentBal("ryan"));
        u.setCurrentBal("ryan", 1000);
        Assertions.assertEquals(1000, u.findCurrentBal("ryan"));
    }
    
    
    /** 
     * Tests writeToFile method with valid user data
     * @throws NullPointerException
     */
    @DisplayName("write user to file pass")
    @Test
    public void passWriteFile() throws NullPointerException {
        String[] data = {"yesyesyes",	"yesyesyes","yesyesyes@yesyesyes.com","yesyes",	"20",	"500",	"0",	"false",	"false",	"none",	"null",	"0",	"04/01/22"};
        User u = new User(data);
        RegisterDAO d = new RegisterDAO();
        //Assertions.assertAll(() -> d.writeToFile(u));
    }

    
    /** 
     * Tests forgotUsername with valid username
     * @throws NullPointerException
     */
    @DisplayName("forgotUsername pass")
    @Test
    public void passForgotUser() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("ryan", f.findUsername("ryan@gmail.com"));
    }

    
    /** 
     * Tests forgotPassword method with valid username
     * @throws NullPointerException
     */
    @DisplayName("forgotPassword pass")
    @Test
    public void passForgotPass() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("ryan", f.findPassword("ryan"));
    }
    
    
    /** 
     * Tests forgotUsername method with invalid email
     * @throws NullPointerException
     */
    @DisplayName("forgotUsername fail")
    @Test
    public void failForgotUser() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("User not found", f.findUsername("ryan@gmail.comasdadqsdsad"));
    }
    
    
    /** 
     * Tests forgotPassword method with invalid username
     * @throws NullPointerException
     */
    @DisplayName("forgotPassword fail")
    @Test
    public void failForgotPass() throws NullPointerException {
        ForgotDAO f = new ForgotDAO();
        Assertions.assertEquals("User not found", f.findPassword("ryannnnnnasdnsadn"));
    }
    
    /**
     * Tests getOwner equals method with non-equal value
     */
    @Test
    public void characterGetOwnerBad() {
    	Character c = new Character();
    	c.setOwner("Jack");
    	assert(!c.getOwner().equals("Ben"));
    }
    
    /**
     * Tests getOwner equals method with equal value
     */
    @Test
    public void characterGetOwnerGood() {
    	Character c = new Character();
    	c.setOwner("Jack");
    	assert(c.getOwner().equals("Jack"));
    }
    
    /** 
     * Tests findChar method with valid character
     * @throws Exception
     */
    @DisplayName("findChar (pass)")
    @Test
    public void passFindChar() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Character c = new Character("Speed",	"Earth",	"Stupid",	1,	0,	"https://i.ytimg.com/vi/wYZux3BMc5k/maxresdefault.jpg",	"Cyril");
        Character d = ch.findChar("Speed", "Earth");
        Assertions.assertEquals(c, d);
    }

    
    /** 
     * Tests VotingBoothDAO constructor
     * @throws Exception
     */
    @DisplayName("VotingBooth (pass)")
    @Test
    public void passVoting() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Character a = ch.findChar("Kirby", "DreamLand");
        Character b = ch.findChar("Spider-Ham", "New York");

        Assertions.assertAll(() -> new VotingBoothDAO(a, b));
    }

    
    /** 
     * Tests Character makeList method which fetches all characters
     * @throws Exception
     */
    @DisplayName("makeList (pass)")
    @Test
    public void passMakeList() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        ArrayList<Character> list = ch.makeList();
        Assertions.assertTrue(list.size() > 0);
    }

    
    /** 
     * Tests doesCharExist with valid character
     * @throws Exception
     */
    @DisplayName("charExist (pass)")
    @Test
    public void passExists() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Assertions.assertTrue(ch.doesCharExist("Speed", "Earth"));
    }
    
    /** 
     * Tests doesCharExist with invalid character
     * @throws Exception
     */
    @DisplayName("charExist (fail)")
    @Test
    public void failExists() throws Exception {
        CharacterDAO ch = new CharacterDAO();
        Assertions.assertFalse(ch.doesCharExist("Speeddddddd", "Earth"));
    }

    
    /** 
     * Tests shoeBalance method with valid username
     * @throws Exception
     */
    @DisplayName("showBal (pass)")
    @Test
    public void passBal() throws Exception {
        DailyCheckIn d = new DailyCheckIn();
        Assertions.assertAll(() -> d.showBalance("ryan"));
    }


    
    /** 
     * Tests validatePassword with valid username and password
     * @throws Exception
     */
    @DisplayName("validatePassword (pass)")
    @Test
    public void passValidatePassword() throws Exception {
        Assertions.assertTrue(loginDAO.validatePassword("ryan", "ryan"));
    }

    
    /** 
     * Tests validatePassword with invalid username/password
     * @throws Exception
     */
    @DisplayName("validatePassword (fail)")
    @Test
    public void failValidatePassword() throws Exception {
        Assertions.assertFalse(loginDAO.validatePassword("ryan", "ryanDoesNotHaveThisPassword"));
    }
    
    /** 
     * Tests getCharacters method
     * @throws Exception
     */
    @DisplayName("getCharacters (pass)")
    @Test
    public void passGetCharacters() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        ArrayList<CharacterVotes> list;
        Assertions.assertAll(() -> ch.getCharacterVotes()) ;

    }

    
    /** 
     * Tests updateCharacterVotes 
     * @throws Exception
     */
    @DisplayName("update Char votes (pass)")
    @Test
    public void passUpdateCharVotes() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        //Assertions.assertAll(() -> ch.updateCharacterVotes(new CharacterVotes()));
    } //might not be a good test, dont wanna overwite important data

    
    /** 
     * Tests getCharacterVoteByUsername with valid username
     * @throws Exception
     */
    @DisplayName("update Char votes (pass)")
    @Test
    public void passGetCharVote() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        //Assertions.assertAll(() -> ch.getCharacterVoteByUsername("jack"));
    }

    
    /** 
     * Tests findCurrentVote with valid username and vote index
     * @throws Exception
     */
    @DisplayName("get Vote (pass)")
    @Test
    public void passGetVote() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        Assertions.assertAll(() -> ch.findCurrentVote("ryan", 1));
    }
    
    /** 
     * Tests findCurrentBet with valid username and bet index
     * @throws Exception
     */
    @DisplayName("get bet (pass)")
    @Test
    public void passGetBet() throws Exception {
        CharacterVotesDAO ch = new CharacterVotesDAO();
        Assertions.assertAll(() -> ch.findCurrentBet("ryan", 1));
    }



    //FIXME TERMINATED TEST CASES

    /**
     * Tests addImagesToBracket with valid character
     */
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
    @Test
    public void addImagesToBracketFail()
    {
        TournamentBracketPanel p = new TournamentBracketPanel();
        assertThrows(MalformedURLException.class, () -> {
            p.addImagesToBracket(new Character[1], new JLayeredPane(), 0, 0, 0, 0, 0, 1);}, "malrformed URL excpetion expected");
    }

    /*
    Get round1, round2, round3, and winner functions all solely use addImagesToBracket with different parameters.
    Thus, testing addImagesToBracket encompasses all testing for the class.
     */

    /**
     * Tests getCharacterRoundsLines exist
     */
    @Test
    public void getCharacterRoundLinesSuccess()
    {
        long lines = -1;
        CharacterDAO myCharDAO = new CharacterDAO();
        lines = myCharDAO.getCharacterRoundsLines();

        assertTrue(lines >= 0);
    }
    
    /**
     * Tests UserFile export to excel with valid path
     */
    @Test
    public void exportUsersToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	UserDAO myUserDAO = new UserDAO();
    	
    	assertDoesNotThrow(() -> {
    		myUserDAO.exportToExcel(homePath);
    	});
    }
    
    /**
     * Tests UserFile expor to excel with invalid path
     */
    @Test
    public void exportUsersToExcelFail() {
    	UserDAO myUserDAO = new UserDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myUserDAO.exportToExcel("asfasfawr322asfaf");
    	});
    }
    
    /**
     * Tests CharacterFile export to excel with valid path
     */
    @Test
    public void exportCharactersToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertDoesNotThrow(() -> {
    		myCharDAO.exportCharactersToExcel(homePath);
    	});
    }
    
    /**
     * Tests CharacterFile export to excel with invalid path
     */
    @Test
    public void exportCharactersToExcelFail() {
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myCharDAO.exportCharactersToExcel("asfasd2easkljf");
    	});
    }
    
    /**
     * Tests CharacterRounds export to excel with valid path
     */
    @Test
    public void exportCharacterRoundsToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertDoesNotThrow(() -> {
    		myCharDAO.exportCharactersToExcel(homePath);
    	});
    }
    
    /**
     * Tests CharacterRounds export to excel with invalid path
     */
    @Test
    public void exportCharacterRoundsToExcelFail() {
    	CharacterDAO myCharDAO = new CharacterDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myCharDAO.exportCharacterRoundsToExcel("asfasfasfasfasf");
    	});
    }
    
    /**
     * Tests CharacterVotes export to excel with valid path
     */
    @Test
    public void exportCharacterVotesToExcelSuccess() {
    	String homePath = System.getProperty("user.home");
    	CharacterVotesDAO myCharVotesDAO = new CharacterVotesDAO();
    	
    	assertDoesNotThrow(() -> {
    		myCharVotesDAO.exportToExcel(homePath);
    	});
    }
    
    /**
     * Tests CharacterVotes export to excel with invalid path
     */
    @Test
    public void exportCharacterVotesToExcelFail() {
    	CharacterVotesDAO myCharVotesDAO = new CharacterVotesDAO();
    	
    	assertThrows(IOException.class, () -> {
    		myCharVotesDAO.exportToExcel("asfasf32rasgasbas");
    	});
    }

    /**
     * Tests CharacterName validation
     */
    //create character
    @Test
    public void passValidCharName(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidCharName("valid"));
    }

    /**
     * Tests CharacterName validation
     */
    @Test
    public void failValidCharName(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidCharName("N0t_v@lid afadasdfugsfafsdfsdafjkbsvn sdfvjhl dhlvj djhv {}|"));
    }

    /**
     * Tests world validation
     */
    @Test
    public void passValidWorld(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidWorld("valid"));
    }

    /**
     * Tests world validation
     */
    @Test
    public void failValidWorld(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidWorld("N0t_v@lid afadasdfugsfafsdfsdafjkbsvn sdfvjhl dhlvj djhv {}|"));
    }

    /**
     * Tests character description validation
     */
    @Test
    public void passValidCharDesc(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidCharDesc("valid"));
    }

    /**
     * Tests character description validation
     */
    @Test
    public void failValidCharDesc(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidCharDesc("N0t_v@lid afadasdfugsfafsdfsdafjkbsvn sdfvjhl dhlvj djhv {}|"));
    }

    /**
     * Tests character url validation
     */
    @Test
    public void passValidCharUrl(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertTrue(() -> ch.isValidCharURL("https://upload.wikimedia.org/wikipedia/en/d/d7/Harry_Potter_character_poster.jpg"));
    }

    /**
     * Tests character url validation
     */
    @Test
    public void failValidCharUrl(){
        CreateCharacter ch = new CreateCharacter();
        Assertions.assertFalse(() -> ch.isValidCharURL("this is not a url"));
    }

    /**
     * Tests beginRegistration init
     */
    //@Test
    //public void registration(){
    //    Register r = new Register();
    //    Assertions.assertAll(() -> r.beginRegistration());
    //}

    /**
     * Tests verifyName with valid name
     */
    @Test
    public void passName(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyName("validName"));
    }

    /**
     * Tests verifyName with invalid name
     */
    @Test
    public void failName(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyName("Inv@l!d nAmmmmmmeeeeeefewb$%^&{}|FHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }

    /**
     * Tests verifyPassword with valid password
     */
    @Test
    public void passPassword(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyPassword("validPassword"));
    }

    /**
     * Tests verifyPassword with invalid password
     */
    @Test
    public void failPassword(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyPassword("Inv@l!d nAmmmmmmeeeee$&()_|efewbFHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }

    /**
     * Tests verifyAge with valid name
     */
    @Test
    public void passAge(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyAge("20"));
    }

    /**
     * Tests verifyAge with invalid name
     */
    @Test
    public void failAge(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyAge("Inv@l!d nAmmmmmmeeeeeefewbFHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }

    /**
     * Tests verifyEmail with valid email
     */
    @Test
    public void passEmail(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyEmail("valid@valid.com"));
    }

    /**
     * Tests verifyEmail with invalid email
     */
    @Test
    public void failEmail(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyEmail("Inv@l!d nAmmmmmmeeeeeefewbFHLWFBHEBDVHAVHABSDVHBDASVHA SH"));
    }

    /**
     * Tests verifyInputSizeIsValid with valid input
     */
    @Test
    public void passInputSize(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyInputSizeIsValid(2, 8, "valid"));
    }

    /**
     * Tests verifyInputSizeIsValid with invalid input
     */
    @Test
    public void failInputSize(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyInputSizeIsValid(2, 3, "not valid"));
    }

    /**
     * Tests verifyAgeLength with valid age
     */
    @Test
    public void passAgeLength(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyAgeLength("20"));
    }

    /**
     * Tests verifyAgeLength with invalid age
     */
    @Test
    public void failAgeLength(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyAgeLength("333333"));
    }

    /**
     * Tests verifyAgeIsNumeric with valid age
     */
    @Test
    public void passAgeNumeric(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyAgeIsNumeric("2"));
    }

    /**
     * Tests verifyAgeIsNumeric with invalid age
     */
    @Test
    public void failAgeNumeric(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyAgeIsNumeric("twelve"));
    }

    /**
     * Tests verifyOlderThanEighteen with valid age
     */
    @Test
    public void passAgeOlder(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyOlderThanEighteen("20"));
    }

    @Test
    public void failAgeOlder(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyOlderThanEighteen("2"));
    }

    /**
     * Tests verifyYoungerThanTwoHundred with valid age
     */
    @Test
    public void passAgeYounger(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyYoungerThanTwoHundred("20"));
    }

    /**
     * Tests verifyYoungerThanTwoHundred with invalid age
     */
    @Test
    public void failAgeYounger(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyYoungerThanTwoHundred("333"));
    }

    /**
     * Tests verifyInputIsDigitOrLetter with valid input
     */
    @Test
    public void passInputAlphaDigit(){
        Register r = new Register();
        Assertions.assertTrue(r.verifyInputIsDigitOrLetter("username1234"));
    }

    /**
     * Tests verifyInputIsDigitOrLetter with invalid input
     */
    @Test
    public void failInputAlphaDigit(){
        Register r = new Register();
        Assertions.assertFalse(r.verifyInputIsDigitOrLetter("N0tV@l!d Usern#m*"));
    }

    //FIXME, Might have to remove these tests because of the pop up dialogs
    /**
     * Tests usernameAnalysis
     */
    //@Test
    //public void passUsernameAnalysis(){
    //    Register r = new Register();
    //    Assertions.assertAll(() -> r.usernameAnalysis());
    //}

    /**
     * Tests passwordAnalysis
     */
    //@Test
    //public void passPasswordAnalysis(){
    //    Register r = new Register();
    //    Assertions.assertAll(() -> r.passwordAnalysis());
    //}

    /**
     * Tests ageAnalysis
     */
    //@Test
    //public void passAgeAnalysis(){
    //    Register r = new Register();
    //    Assertions.assertAll(() -> r.ageAnalysis());
    //}

    /**
     * Tests emailAnalysis
     */
    //@Test
    //public void passEmailAnalysis(){
    //    Register r = new Register();
    //    Assertions.assertAll(() -> r.emailAnalysis());
    //}

    /**
     * Tests isNameValid with valid name
     */
    @Test
    public void passNameValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertTrue(() -> e.isNameValid("newRyan"));
    }

    /**
     * Tests isNameValid with invalid name
     */
    @Test
    public void failNameValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertFalse(() -> e.isNameValid("newRyan{}|()*&#^%$"));
    }

    /**
     * Tests isAgeValid with valid age
     */
    @Test
    public void passAgeValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertTrue(() -> e.isAgeValid("20"));
    }

    /**
     * Tests isAgeValid with non-number
     */
    @Test
    public void failAgeValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertFalse(() -> e.isAgeValid("not a number"));
    }

    /**
     * Tests isDescValid with valid description
     */
    @Test
    public void passDescValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertTrue(() -> e.isDescValid("newRyan"));
    }

    /**
     * Tests isDescValid with description that is too long
     */
    @Test
    public void failDescValid(){
        EditProfile e = new EditProfile("ryan");
        Assertions.assertFalse(() -> e.isDescValid("this line is gonna be way too long and it gonna have some wierd characters cus why not @$@!^*@(#)_@fbahlfbvsdvauvcSD CSDJV VJ ASDJ VDSVAJ VSDAJ VCJHASDFBSD CJSDBCDJS CSD CJHDS C CAK"));
    }



    //todo left, edit profile, and added character dao functions (fix those tests)





}
