package adf.communication.standard.bundle;


import adf.communication.standard.util.BitOutputStream;

public class MessageDummy extends StandardMessage
{
	final private int SIZE_TEST = 32;
	private int dummyTest;

	public MessageDummy(boolean isRadio, int test)
	{
		super(isRadio);
		dummyTest = test;
	}

	public MessageDummy(int time, int ttl, boolean isRadio, byte[] data)
	{
		super(time, ttl, isRadio);
	}

	public int getValue() { return this.dummyTest; }

	@Override
	public int getByteArraySize()
	{
		return SIZE_TEST;
	}

	@Override
	public byte[] toByteArray()
	{
		BitOutputStream bitOutputStream = new BitOutputStream();
		bitOutputStream.writeBits(dummyTest, SIZE_TEST);
		return bitOutputStream.toByteArray();
	}
}