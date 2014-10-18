package problems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class person {
	static boolean dontPrintCrap = false;
	static StringBuilder finalAnswer = new StringBuilder();
	static int countOfRounds = 0;
	static int countOfDead = 0;
	static int numberOfPeople = 0;
	boolean holderOfGun = false;
	boolean isDead = false;
	int number = 0;
	person(int n){
		this.number = n;
	}
	person()
	{
		
	}

	private static void accountForAlives(person[] arrayOfPeople){
		System.out.print("Persons alive : ");
		for(int i=0; i<arrayOfPeople.length; i++){
			if(!arrayOfPeople[i].isDead)
			{
				System.out.print(arrayOfPeople[i].number + " ");
			}
		}
		System.out.println("\n");
	}
	
	//returns first alive person
	private static int getAlivePerson(int indexOfGunHolder, person[] arrayOfPeople){
		for(int i = indexOfGunHolder+1; i<arrayOfPeople.length; i++){
			if(!arrayOfPeople[i].isDead)
			{
				return i;
			}
		}
		return -1; //return -1 if no one is deemed alive
	}
	
	private static void recurse(int startIndex, person[] arrayOfPeople){
		if(countOfDead < numberOfPeople-1)
		{
			int nextAlivePerson;
			int personWhoKillsNext;
			nextAlivePerson = getAlivePerson(startIndex, arrayOfPeople);
			
			if(nextAlivePerson == -1)
			{
				nextAlivePerson = getAlivePerson(-1, arrayOfPeople);
			}
			arrayOfPeople[nextAlivePerson].isDead = true;
			countOfDead++;
			
			personWhoKillsNext = getAlivePerson(startIndex, arrayOfPeople);
			if(personWhoKillsNext == -1){
				personWhoKillsNext = getAlivePerson(-1, arrayOfPeople);
				countOfRounds++;
				if(!dontPrintCrap){
					System.out.println("Round number : " + countOfRounds + " finished");
					accountForAlives(arrayOfPeople);
				}
			}
			recurse(personWhoKillsNext, arrayOfPeople);						
		}else{
			if(!dontPrintCrap)
				System.out.println(arrayOfPeople[getAlivePerson(-1, arrayOfPeople)].number + " is the last man standing!");
			else{
				//print to a text file : numberOfPeople, lastManStanding
				finalAnswer.append(numberOfPeople + "," + arrayOfPeople[getAlivePerson(-1, arrayOfPeople)].number + "\n");
			}
		}
	}
	
	private static person[] makePeople(int number){
		person[] arrayOfPeople = new person[number];
		for(int i=0; i<number; i++){
			arrayOfPeople[i] = new person(i+1);
		}
		return arrayOfPeople;
	}
	
	public void excutePerson(boolean dontPrintCrap, int number){
		if(!dontPrintCrap)
		{
			numberOfPeople = number;
			person[] arrayOfPeople = makePeople(numberOfPeople);
			long startTime = Calendar.getInstance().getTimeInMillis();
			recurse(0, arrayOfPeople);
			long endTime = Calendar.getInstance().getTimeInMillis();
			System.out.println("Time taken : " + (endTime-startTime) + "ms");
		}
		else
		{
			//to generate a csv
			boolean writeSuccess = false;
			long startTime = Calendar.getInstance().getTimeInMillis();
			long endTime = 0;
			for(int j = 1; j<=number; j++){
				numberOfPeople = j;
				countOfDead = 0;
				person[] genericPeopleArray = makePeople(j);
				recurse(0, genericPeopleArray);
			}
			//write final answer to a csv file
			File outFile  = new File("C:\\Users\\Raghudevan.S\\Desktop\\findCurve.csv");
			try {
				BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
				output.write(finalAnswer.toString());
				output.close();
				endTime = Calendar.getInstance().getTimeInMillis();
				writeSuccess = true;
			} catch (IOException e) {
				writeSuccess = false;
				e.printStackTrace();
			}finally{
				if(writeSuccess)
					System.out.println("Write to csv success! Time taken : " + (endTime-startTime) + "ms" );
			}
			
		}
	}
}
