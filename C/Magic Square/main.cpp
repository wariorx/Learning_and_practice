//Raul Calcamo
//Date:11/26/2015
//The program generates and fills a magic square. The values in each square are randomly assigned, but they must add up to the same value
//the numbers must also be unique, meaning that there wont be two 1s in the same square for example
//if they do not add to the same value, the program will fill the square with new random numbers and repeat the process
#include <iostream>
#include<cstdlib>
#include<ctime>


//global variables, maximum and minimum values of the square, size of array
const int maximum=9;
const int minimum=1;
const int SIZE=3;
//functions

bool checkUnique(//const int store [][SIZE],
                 const int x, int& a, int store[9]); //this function checks if the number has already been assigned to the array. If it has not, it returns true
bool magicSquareFunc(const int magicSquare[][SIZE], int store[9]); //this function checks that all rows and columns add up to the same value. If they do, it returns true
void show(const int magicSquare[][SIZE]); //displays the square on the screen
int vertical1(const int magicSquare[][SIZE]);//this function will be used to add vertical values of the first column
int vertical2(const int magicSquare[][SIZE]);//this function will be used to add vertical values of the second column
int vertical3(const int magicSquare[][SIZE]);//this function will be used to add vertical values of the third column
int horizontal1(const int magicSquare[][SIZE]);//this function will be used to add the horizontal values of the first row
int horizontal2(const int magicSquare[][SIZE]);//this function will be used to add the horizontal values of the second row
int horizontal3(const int magicSquare[][SIZE]);//this function will be used to add the horizontal values of the third row
int diagonal1(const int magicSquare[][SIZE]); //this function will be used to add the diagonal values of the left to right diagonal
int diagonal2(const int magicSquare[][SIZE]);//this function will be used to add the diagonal values of the right to left diagonal

using namespace std;

int main()
{

    //all values are set to 0 in order to prevent junk
   int magicSquare[SIZE][SIZE]={
   {0,0,0},
   {0,0,0},
   {0,0,0}
   }; //stores the values for magic square

   bool magic=true; //value returned from magic square function

   int store[9]={0,10,11,112,12,13,14,15,16};//holds the value for the random number temporarily to prevent it from being repeated

   int seed=time(0); //seed for the RNG
   int x;

   int a=0; //used to store values in store, will be passed to check

  // bool loop; //variable used to loop in case the number is not unique


   srand(seed);//RNG

    label2: //sent here after magic square failed

   for (int i=0; i<SIZE; i++) //Rows
   {
       for(int j=0; j<SIZE; j++) // Columns
       {
          // do{//will repeat until it determines all values to be unique

           label1: //if the number is not unique, the program goes here. This prevents the iteration of the loop,
                    //so that all values do not have to be reset every time

            bool checked=false; // will store the value returned from check unique

                x= ( rand() % ( maximum - minimum + 1) + minimum ); //generate random number between 1 and 9

                //store[i][j]=x; //assign random number to the array location


                checked=checkUnique( //store,
                                     x, a, store );
                //cout<<checked<<endl;

                if (checked==true)
                {
                    //magicSquare[i][j] = store[i][j];
                    magicSquare[i][j]=x;

                    continue;

                }

                else
                    goto label1;
                    //loop=checked;

               // }while(loop==false);
        }

    }


  //}while (checked== false);


show( magicSquare); //display the square

magic=magicSquareFunc(magicSquare, store); //check for th magic square

if (magic==false)
    goto label2;

  return 0;

}

bool checkUnique( //const int store[][SIZE],
                 const int x, int& a, int store[9])
{
     bool checked=false;

    for(int counter=0; counter<9; counter++)
    {
        if (x!=store[counter])
        {
            checked=true;
            continue;

        }

        else if (x==store[counter])
        {
            checked=false;
            break;
        }
    }

    if (checked==true)
    {
        store[a]=x;
        a++;
    }

    if(a==8)
    {
        a=0;
    }

    return checked;
}

void show(const int magicSquare[][SIZE])
{
     for(int i=0; i<SIZE; i++) //this loop will do the cout, rows
  {
      for (int j=0; j<SIZE; j++) //this will do the cout, columns
      {
          cout<<magicSquare[i][j]<<" ";
      }

      cout<<"\n"; //makes the screen display show as a square

  }
}

bool magicSquareFunc(const int magicSquare[][SIZE], int store[9])
{
    bool magic;

    int horizontal_value1=0,
    horizontal_value2=0,
    horizontal_value3=0,
    vertical_value1=0,
    vertical_value2=0,
    vertical_value3=0,
    diagonal_value1=0,
    diagonal_value2=0;

    //calculate the vertical values
    vertical_value1=vertical1(magicSquare);

    vertical_value2=vertical2(magicSquare);

    vertical_value3=vertical3(magicSquare);

    //calculate the horizontal values
    horizontal_value1=horizontal1(magicSquare);

    horizontal_value2=horizontal2(magicSquare);

    horizontal_value3=horizontal3(magicSquare);

    //calculate the diagonal values
    diagonal_value1=diagonal1(magicSquare);

    diagonal_value2=diagonal2(magicSquare);

    //check if all vertical, horizontal and diagonal values are the same
    if (vertical_value1 == vertical_value2 && vertical_value2==vertical_value3 && vertical_value3==horizontal_value1 &&
        horizontal_value1==horizontal_value2 && horizontal_value2==horizontal_value3 && horizontal_value3==diagonal_value1 &&
        diagonal_value1==diagonal_value2)
        {
            cout<<"Magic Square!!!"<<endl;

            magic=true;
        }


    //if they're not, not a magic square will be displayed and main will be called to create another combination
    else
    {
        cout<<"Not a magic Square..."<<endl;
        cout<<"-------------------"<<endl;

        for(int i=0,j=0; i<9; i++, j+=10) //works as a store reset; assigns values that will not be produced by the RNG to store
        {
            store[i]=j;

        }

        magic=false;
        //main(); //call main to redo the previous processes
    }


return magic;
}

int vertical1(const int magicSquare[][SIZE])
{
    int value=0;

    int columns=0;

    for(int rows=0; rows<SIZE; rows++)
    {
        value+=magicSquare[rows][columns];
    }

    return value;
}

int vertical2(const int magicSquare[][SIZE])
{
    int value=0;

    int columns=1;

    for(int rows=0; rows<SIZE; rows++)
    {
        value+=magicSquare[rows][columns];
    }

    return value;
}

int vertical3(const int magicSquare[][SIZE])
{
    int value=0;

    int columns=2;

    for(int rows=0; rows<SIZE; rows++)
    {
        value+=magicSquare[rows][columns];
    }

    return value;
}

int horizontal1(const int magicSquare[][SIZE])
{
    int value=0;

    int rows=0;

    for (int columns=0; columns<SIZE; columns++)
    {
        value+=magicSquare[rows][columns];
    }

    return value;
}

int horizontal2(const int magicSquare[][SIZE])
{
    int value=0;

    int rows=1;

    for (int columns=0; columns<SIZE; columns++)
    {
        value+=magicSquare[rows][columns];
    }

    return value;
}

int horizontal3(const int magicSquare[][SIZE])
{
    int value=0;

    int rows=2;

    for (int columns=0; columns<SIZE; columns++)
    {
        value+=magicSquare[rows][columns];
    }

    return value;
}

int diagonal1(const int magicSquare[][SIZE])
{
    int value=0;

    value= magicSquare[0][0] + magicSquare[1][1] + magicSquare[2][2];

    return value;
}

int diagonal2(const int magicSquare[][SIZE])
{
    int value=0;

    value= magicSquare[0][2] + magicSquare[1][1] + magicSquare[2][0];

    return value;
}

