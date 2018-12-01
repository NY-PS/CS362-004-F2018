/**
 * @author: Pulak Saha
 * @description: This file contains a unit test for the whoseTurn function in dominion.c
**/

#include <limits.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"

#define FN_NAME "whoseTurn"

void assert_true(int expected_outcome, int actual_outcome, char* fn_name)
{
    if (expected_outcome == actual_outcome)
        printf("TEST SUCCESSFULLY COMPLETED (%s) - expected outcome: %d \t actual outcome: %d\n", fn_name, expected_outcome, actual_outcome);
    else
        printf("TEST FAILED (%s) - expected outcome: %d \t actual outcome: %d\n", fn_name, expected_outcome, actual_outcome);
}

int main(int argc, char** argv)
{
    srand(time(NULL));

    struct gameState state;
    int numPlayers = 2;
    int kCards[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, sea_hag, tribute, smithy};
    int randomSeed = rand() % INT_MAX;
    int i;

    initializeGame(numPlayers, kCards, randomSeed, &state);

    printf("---------- Started testing function: %s ----------\n", FN_NAME);

    int isError;
    isError = 0;

    for (i = 0; i < 10; i++)
    {
        state.whoseTurn = i;

        if (whoseTurn(&state) != i)
        {
            isError = 1;
            break;
        }
    }

    assert_true(0, isError, FN_NAME);

    printf("---------- Finished testing function: %s ----------\n\n", FN_NAME);

    return 0;
}