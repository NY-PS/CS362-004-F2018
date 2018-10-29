/**
 * @author: Pulak Saha
 * @description: This file contains a unit test for the buyCard function in dominion.c
**/

#include <limits.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"

#define FN_NAME "buyCard"

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

    initializeGame(numPlayers, kCards, randomSeed, &state);

    printf("---------- Started testing function: %s ----------\n", FN_NAME);

    int isError;

    isError = 0;
    state.whoseTurn = 0;

    // test if card can be bought under normal conditions
    printf("* Test Case: Card can be bought under normal conditions *\n");
    state.numBuys = 10;
    state.coins = 10;
    isError = buyCard(smithy, &state);
    assert_true(0, isError, FN_NAME);

    // test if card can be bought if no buys are left in the current round
    printf("* Test Case: Card cannot be bought if no more buys for the round *\n");
    state.numBuys = 0;
    isError = buyCard(smithy, &state);
    assert_true(-1, isError, FN_NAME);

    // test if card can be bought if no more supply left
    printf("* Test Case: Card cannot be bought if no more left in supply *\n");
    state.numBuys = 10;
    state.supplyCount[smithy] = 0;
    isError = buyCard(smithy, &state);
    assert_true(-1, isError, FN_NAME);

    // test if card can be bought if money is not enough
    printf("* Test Case: Card cannot be bought if player does not have enough money *\n");
    state.numBuys = 10;
    state.coins = 0;
    isError = buyCard(smithy, &state);
    assert_true(-1, isError, FN_NAME);

    printf("---------- Finished testing function: %s ----------\n\n", FN_NAME);

    return 0;
}
