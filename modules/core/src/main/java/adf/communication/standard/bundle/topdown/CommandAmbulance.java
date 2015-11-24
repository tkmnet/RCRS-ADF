package adf.communication.standard.bundle.topdown;

import adf.communication.standard.bundle.StandardMessage;
import adf.communication.util.BitStreamReader;
import rescuecore2.worldmodel.EntityID;
import comlib.message.MessageCommand;
import comlib.message.MessageID;

public class CommandAmbulance extends StandardMessage
{
	/* below id is same to information.MessageAmbulanceTeam */
	public static final int ACTION_REST = 0;
	public static final int ACTION_MOVE = 1;
	public static final int ACTION_RESCUE = 2;
	public static final int ACTION_LOAD = 3;

	protected int rawToID;
	protected int rawTargetID;
	protected EntityID commandToID;
	protected EntityID commandTargetID;
	private int myAction;

	public CommandAmbulance(boolean isRadio, EntityID toID, EntityID targetID, int action)
	{
		super(isRadio);
		this.commandToID = toID;
		this.commandTargetID = targetID;
		this.myAction = action;
	}

	public CommandAmbulance(boolean isRadio, int from, int ttl, BitStreamReader bitStreamReader)
	{
		super(isRadio, from, ttl, bitStreamReader);
		rawToID = bitStreamReader.getBits(SIZE_TO);
		rawTargetID = bitStreamReader.getBits(SIZE_TARGET);
	}

	public int getAction()
	{ return myAction; }

	@Override
	public int getByteArraySize() {
		return 0;
	}

	@Override
	public byte[] toByteArray() {
		return new byte[0];
	}

	public EntityID getToID()
	{
		if ( commandToID == null )
		{ commandToID = new EntityID(rawToID); }
		return commandToID;
	}

	public EntityID getTargetID()
	{
		if ( commandTargetID == null )
		{ commandTargetID = new EntityID(rawTargetID); }
		return commandTargetID;
	}
}
