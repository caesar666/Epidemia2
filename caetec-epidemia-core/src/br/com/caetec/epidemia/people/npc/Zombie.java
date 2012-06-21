package br.com.caetec.epidemia.people.npc;

import br.com.caetec.epidemia.controller.move.MoveNPC;
import br.com.caetec.epidemia.people.Humanoid;

public class Zombie extends Humanoid
{
	private MoveNPC move;

	public MoveNPC getMove()
	{
		return move;
	}

	public void setMove(MoveNPC move)
	{
		this.move = move;
	}
}
