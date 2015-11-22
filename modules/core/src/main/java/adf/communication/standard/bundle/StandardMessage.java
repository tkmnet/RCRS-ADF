package adf.communication.standard.bundle;

import adf.communication.CommunicationMessage;
import adf.communication.standard.util.BitStreamReader;
import rescuecore2.worldmodel.EntityID;

abstract public class StandardMessage extends CommunicationMessage
{
	int rawSenderID = -1;
	EntityID mySenderID;
	int ttl = -1;

	public StandardMessage(boolean isRadio)
	{
		super(isRadio);
	}

	public StandardMessage(boolean isRadio, int senderID, int ttl, BitStreamReader bitStreamReader)
	{
		super(isRadio);
		this.rawSenderID = senderID;
		this.ttl = ttl;
	}

	public EntityID getSenderID()
	{
		if ( mySenderID == null )
		{ mySenderID = new EntityID(rawSenderID); }
		return mySenderID;
	}
}
