package adf.communication.standard.bundle.topdown;

import adf.communication.standard.bundle.StandardMessage;
import adf.communication.util.BitOutputStream;
import adf.communication.util.BitStreamReader;
import rescuecore2.worldmodel.EntityID;

public class MessageReport extends StandardMessage
{
	private static final int SIZE_DONE = 1;
	private boolean reportDone;

	public MessageReport(boolean isRadio, boolean isDone)
	{
		super(isRadio);
		reportDone = isDone;
	}

	public MessageReport(boolean isRadio, int from, int ttl, BitStreamReader bitStreamReader)
	{
		super(isRadio, from, ttl, bitStreamReader);
		reportDone = (0 == bitStreamReader.getBits(SIZE_DONE)?false:true);
	}

	public boolean isDone()
	{ return this.reportDone; }

	public boolean isFailed()
	{ return !this.reportDone; }

	@Override
	public int getByteArraySize() {
		return SIZE_DONE;
	}

	@Override
	public byte[] toByteArray() {
		BitOutputStream bitOutputStream = new BitOutputStream();
		bitOutputStream.writeBits((reportDone?1:0), SIZE_DONE);
		return bitOutputStream.toByteArray();
	}
}
