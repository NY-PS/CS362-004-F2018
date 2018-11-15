/**
 * @author: Pulak Saha
 * @description: This file contains a random tester for the Adventurer card effect from dominion.c
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

#define CARD_NAME "Adventurer"
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

void checkAdventurerCardEffect(int player, struct gameState *post, int tempHand[])
{
    // create a separate copy of post to compare values before and after test
    struct gameState pre;
    memcpy(&pre, post, sizeof(struct gameState));

    // call card effect
    handleAdventurerCardEffect(0, post, player, tempHand, 0);

    // update variables in gameState copy after a draw with expected outcome
    pre.handCount[player] += 2;
    pre.discardCount[player] = (pre.deckCount[player] - 2) - post->deckCount[player];

    // assertions
    assertTrue(pre.handCount[player], post->handCount[player], "+2 Cards");
    assertTrue(pre.discardCount[player], post->discardCount[player], "+n Discard");
    printf("\n");
}

int main(int argc, char **argv)
{
    srand(time(NULL));
    SelectStream(2);
    PutSeed(3);

    int i, n, p;
    int tempHand[MAX_HAND];
    struct gameState G;

    printf("Testing %s.\n", CARD_NAME);
    printf("RANDOM TESTS.\n");

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

        // randomize deck
        for (i = 0; i < G.deckCount[p]; i++)
        {
            G.deck[p][i] = rand() % 26;
        }

        // manually insert two treasure cards into deck
        int c1 = rand() % G.deckCount[p];
        int c2 = rand() % G.deckCount[p];
        G.deck[p][c1] = copper;
        G.deck[p][c2] = gold;

        // run test
        checkAdventurerCardEffect(p, &G, tempHand);
    }

    printf("ALL TESTS OK\n");

    return 0;
}
