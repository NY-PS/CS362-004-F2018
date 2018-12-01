/**
 * @author: Pulak Saha
 * @description: This file contains a card test for the implementation of Adventurer.
**/

#include <limits.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "dominion.h"
#include "dominion_helpers.h"
#include "rngs.h"

#define CARD_NAME "Adventurer"

void assert_true(int expected_outcome, int actual_outcome, char* card_name)
{
    if (expected_outcome == actual_outcome)
        printf("TEST SUCCESSFULLY COMPLETED (%s) - expected outcome: %d \t actual outcome: %d\n", card_name, expected_outcome, actual_outcome);
    else
        printf("TEST FAILED (%s) - expected outcome: %d \t actual outcome: %d\n", card_name, expected_outcome, actual_outcome);
}

int main(int argc, char** argv)
{
    srand(time(NULL));

    struct gameState state;
    int numPlayers = 2;
    int kCards[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, sea_hag, tribute, smithy};
    int randomSeed = rand() % INT_MAX;

    initializeGame(numPlayers, kCards, randomSeed, &state);

    printf("---------- Started testing card: %s ----------\n", CARD_NAME);

    int choice1 = 0;
    int choice2 = 0;
    int choice3 = 0;
    int handPos = 0;
    int bonus = 0;

    cardEffect(adventurer, choice1, choice2, choice3, &state, handPos, &bonus);

    // test if playedCardCount increased after call to cardEffect
    printf("* Test Case: playedCardCount does not increase after call to cardEffect *\n");
    assert_true(0, state.playedCardCount, CARD_NAME);
    // test if handCount is 7 after playing adventurer
    printf("* Test Case: handCount is 7 after playing adventurer *\n");
    assert_true(7, state.handCount[0], CARD_NAME);

    printf("---------- Finished testing card: %s ----------\n\n", CARD_NAME);

    return 0;
}