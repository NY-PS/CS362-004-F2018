/**
 * @author: Pulak Saha
 * @description: This file contains a unit test for the isGameOver function in dominion.c
**/

#include <limits.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"

#define FN_NAME "isGameOver"

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
    int kCards[10] = {adventurer, smithy, village, minion, mine, sea_hag, cutpurse, council_room, treasure_map, great_hall};
    int randomSeed = rand() % INT_MAX;

    initializeGame(numPlayers, kCards, randomSeed, &state);

    printf("---------- Started testing function: %s ----------\n", FN_NAME);

    int isError;
    isError = 0;

    // test if game ends when stack of Province cards is not empty
    printf("* Test Case: Game does not end if stack of Province cards is not empty *\n");
    state.supplyCount[province] = 10;
    isError = isGameOver(&state);
    assert_true(0, isError, FN_NAME);

    // test if game ends when supply piles are not empty
    printf("* Test Case: Game does not end if supply piles are full *\n");
    isError = isGameOver(&state);
    assert_true(0, isError, FN_NAME);

    // test if game ends when one supply pile is empty
    printf("* Test Case: Game does not end if one supply pile is empty *\n");
    state.supplyCount[province] = 10;
    state.supplyCount[feast] = 0;
    state.supplyCount[adventurer] = 10;
    state.supplyCount[gardens] = 10;
    isError = isGameOver(&state);
    assert_true(0, isError, FN_NAME);

    // test if game ends when two supply piles are empty
    printf("* Test Case: Game does not end if one supply pile is empty *\n");
    state.supplyCount[province] = 10;
    state.supplyCount[feast] = 0;
    state.supplyCount[adventurer] = 0;
    state.supplyCount[gardens] = 10;
    isError = isGameOver(&state);
    assert_true(0, isError, FN_NAME);

    // test if game ends when stack of Province cards is empty
    printf("* Test Case: Game ends if stack of Province cards is empty *\n");
    state.supplyCount[province] = 0;
    isError = isGameOver(&state);
    assert_true(1, isError, FN_NAME);

    // test if game ends when three supply piles are empty
    printf("* Test Case: Game ends if three supply piles are empty *\n");
    state.supplyCount[province] = 10;
    state.supplyCount[feast] = 0;
    state.supplyCount[adventurer] = 0;
    state.supplyCount[gardens] = 0;
    isError = isGameOver(&state);
    assert_true(1, isError, FN_NAME);

    printf("---------- Finished testing function: %s ----------\n\n", FN_NAME);

    return 0;
}
