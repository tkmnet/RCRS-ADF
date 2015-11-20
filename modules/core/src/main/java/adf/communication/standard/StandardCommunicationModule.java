package adf.communication.standard;

import adf.agent.Agent;
import adf.communication.CommunicationMessage;
import adf.communication.CommunicationModule;
import adf.communication.MessageManager;
import adf.communication.standard.util.BitOutputStream;

public class StandardCommunicationModule extends CommunicationModule
{
    final private int SIZE_ID = 5;

    @Override
    public void receive(Agent agent, MessageManager messageManager)
    {

    }

    @Override
    public void send(Agent agent, MessageManager messageManager)
    {
        for (CommunicationMessage message : messageManager.getSendMessageList())
        {
            int messageClassIndex = messageManager.getMessageClassIndex(message);

            BitOutputStream bitOutputStream = new BitOutputStream();
            bitOutputStream.writeBits(messageClassIndex, SIZE_ID);

            if (message.isRadio())
            {
            }
            else
            {
            }
        }
    }
}
