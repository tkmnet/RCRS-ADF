package adf.communication.standard.bundle;

import adf.communication.CommunicationMessage;

abstract public class StandardMessage extends CommunicationMessage
{
	int time;
	int ttl;

	public StandardMessage(boolean isRadio)
	{
		super(isRadio);
	}

	public StandardMessage(int ttl, boolean isRadio)
	{
		super(isRadio);
		this.time = time;
		this.ttl = ttl;
	}
}
