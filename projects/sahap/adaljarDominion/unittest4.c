/**
 * @author: Pulak Saha
 * @description: This file contains a unit test for the scoreFor function in dominion.c
**/

#include <limits.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"

#define FN_NAME "scoreFor"

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
    int kCards[10] = {adventurer, embargo, village, outpost, mine, cutpurse, sea_hag, great_hall, smithy, council_room};
    int randomSeed = rand() % INT_MAX;

    initializeGame(numPlayers, kCards, randomSeed, &state);

    printf("---------- Started testing function: %s ----------\n", FN_NAME);

    int isError;

    isError = 0;
    state.whoseTurn = 0;

    // test if initial score for P1 is 0 if no turns are played yet
    printf("* Test Case: Initial score for Player 1 is 0 if no turns played yet *\n");
    isError = scoreFor(0, &state);
    assert_true(0, isError, FN_NAME);

    // test if initial score for P2 is 0 if no turns are played yet
    printf("* Test Case: Initial score for Player 2 is 0 if no turns played yet *\n");
    isError = scoreFor(1, &state);
    assert_true(0, isError, FN_NAME);

    printf("---------- Finished testing function: %s ----------\n\n", FN_NAME);

    return 0;
}