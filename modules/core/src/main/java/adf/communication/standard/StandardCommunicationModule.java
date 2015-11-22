package adf.communication.standard;

import adf.agent.Agent;
import adf.communication.CommunicationMessage;
import adf.communication.CommunicationModule;
import adf.communication.MessageManager;
import adf.communication.standard.util.BitOutputStream;
import rescuecore2.messages.Message;
import rescuecore2.standard.messages.AKSpeak;

import java.util.List;

public class StandardCommunicationModule extends CommunicationModule
{
    final private int SIZE_ID = 5;

    private int channel = 1;

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
            bitOutputStream.write(message.toByteArray(), 0, message.getByteArraySize());

            if (message.isRadio())
            {
                agent.send((List<Message>)new AKSpeak(agent.getID(), agent.agentInfo.getTime(), channel, bitOutputStream.toByteArray()));
            }
            else
            {
            }
        }
    }
}
