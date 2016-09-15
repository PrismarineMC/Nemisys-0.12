package org.itxtech.nemisys.network.protocol.mcpe;

import org.itxtech.nemisys.utils.Skin;

import java.util.UUID;

/**
 * Created by on 15-10-13.
 */
public class LoginPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.LOGIN_PACKET;

    public byte[] cacheBuffer = new byte[0];

    public String username;

    public int protocol1;
    public int protocol2;

    public long clientId;
    public UUID clientUUID;

    public String serverAddress;
    public String clientSecret;

    public Skin skin;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void decode() {
        this.cacheBuffer = this.getBuffer();
        this.username = this.getString();
        this.protocol1 = this.getInt();
        this.protocol2 = this.getInt();
        if (protocol1 < ProtocolInfo.CURRENT_PROTOCOL) {
            this.setBuffer(null);
            this.setOffset(0);
            return;
        }
        this.clientId = this.getLong();
        this.clientUUID = this.getUUID();
        this.serverAddress = this.getString();
        this.clientSecret = this.getString();

        this.skin = this.getSkinData();
    }


    @Override
    public void encode() {

    }

}
