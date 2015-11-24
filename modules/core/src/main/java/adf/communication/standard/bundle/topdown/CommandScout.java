package adf.communication.standard.bundle.topdown;

import adf.communication.standard.bundle.StandardMessage;
import adf.communication.util.BitStreamReader;
import rescuecore2.worldmodel.EntityID;
import comlib.message.MessageCommand;
import comlib.message.MessageID;

public class CommandScout extends StandardMessage
{
	protected int rawToID;
	protected int rawTargetID;
	protected EntityID commandToID;
	protected EntityID commandTargetID;
	private int scoutRange;

	public CommandScout(boolean isRadio, EntityID toID, EntityID targetID, int range)
	{
        super(isRadio);
		this.commandToID = toID;
		this.commandTargetID = targetID;
		this.scoutRange = range;
	}

	public CommandScout(boolean isRadio, int from, int ttl, BitStreamReader bitStreamReader)
	{
		super(isRadio, from, ttl, bitStreamReader);
	}

	public int getRange()
	{ return scoutRange; }

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
