/**
 * @author: Pulak Saha
 * @description: This file contains a random tester for the Great Hall card effect from dominion.c
 * @note: Adapted from code in betterTestDrawCard.c
 **/

#include "dominion.h"
#include "dominion_helpers.h"
#include <assert.h>
#include <math.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "rngs.h"

#define CARD_NAME "Great Hall"
#define TEST_COUNT 500

void assertTrue(int expectedOutcome, int actualOutcome, char *description)
{
    if (expectedOutcome == actualOutcome)
        printf("TEST PASSED (%s) - expected outcome: %d \t actual outcome: %d\n", description, expectedOutcome,
               actualOutcome);
    else
        printf("TEST FAILED (%s) - expected outcome: %d \t actual outcome: %d\n", description, expectedOutcome,
               actualOutcome);
}

void checkGreatHallCardEffect(int player, struct gameState *post, int handPosition)
{
    // create a separate copy of post to compare values before and after test
    struct gameState pre;
    memcpy(&pre, post, sizeof(struct gameState));

    // call card effect
    handleGreatHallCardEffect(player, post, handPosition);

    // update variables in gameState copy after a draw with expected outcome
    pre.numActions++;

    // assertions
    assertTrue(pre.handCount[player], post->handCount[player], "+1 Card");
    assertTrue(pre.numActions, post->numActions, "+1 Action");
    assertTrue(pre.discardCount[player], post->discardCount[player], "+1 Discard");
    printf("\n");
}

int main(int argc, char **argv)
{
    srand(time(NULL));
    SelectStream(2);
    PutSeed(3);

    int i, n, p, handPosition;
    struct gameState G;

    printf("Testing %s.\n", CARD_NAME);
    printf("RANDOM TESTS.\n");

    handPosition = 0;

    for (n = 0; n < TEST_COUNT; n++)
    {
        for (i = 0; i < sizeof(struct gameState); i++)
        {
            ((char *) &G)[i] = floor(Random() * 256);
        }

        // randomize number of players to be between 1-2
        p = rand() % 2 + 1;

        // randomize deck, hand, discard, played card counts, and num actions
        G.deckCount[p] =  rand() % MAX_DECK + 1;
        G.handCount[p] = rand() % MAX_HAND + 1;
        G.discardCount[p] = rand() % MAX_HAND + 1;
        G.playedCardCount = rand() % MAX_HAND + 1;
        G.numActions = rand() % 50 + 1;

        // randomize handPosition
        handPosition = rand() % G.handCount[p];

        // ensure player's hand contains a Great Hall card
        G.hand[p][handPosition] = great_hall;

        // run test
        checkGreatHallCardEffect(p, &G, handPosition);
    }

    printf("ALL TESTS OK\n");

    return 0;
}
