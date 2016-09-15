package org.itxtech.nemisys.network.protocol.mcpe;

/**
 * Created by on 15-10-12.
 */
public class DisconnectPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.DISCONNECT_PACKET;

    public String message;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.message = this.getString();
    }

    @Override
    public void encode() {
        this.reset();
        this.setBuffer(new byte[]{pid()});
        this.setOffset(0);
        this.putString(this.message);
    }


}
