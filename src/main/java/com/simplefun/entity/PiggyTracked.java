package com.simplefun.entity;

/**
 * Implementiert von PlayerEntity (per Mixin). Liefert einen Server-zu-Client
 * synchronisierten Piggy-Zustand.
 *
 * Hintergrund: Statuseffekte eines Spielers werden in Vanilla NUR an dessen
 * eigenen Client gesendet, nicht an trackende (fremde) Clients. Daher kann der
 * Renderer den Piggy-Effekt fremder Spieler nicht über hasStatusEffect() lesen.
 * Stattdessen wird der Zustand über einen getrackten DataTracker-Wert verteilt,
 * der an ALLE trackenden Clients gesendet wird.
 */
public interface PiggyTracked {
    boolean simplefun$isPiggyTracked();
}
