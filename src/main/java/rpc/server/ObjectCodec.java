package rpc.server;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

public class ObjectCodec extends MessageToMessageCodec<ByteBuf, Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg,
            List<Object> out) throws Exception {
        System.out.println("编码····");
        //Object 转成 byteBuf
        byte[] values = ObjectSerializerUtils.serializer(msg);
        ByteBuf byteBuf = Unpooled.buffer(values.length);
        byteBuf.writeBytes(values);
        //输出
        out.add(byteBuf);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
            List<Object> out) throws Exception {
        System.out.println("解码····");
        byte[] values = new byte[msg.readableBytes()];
        msg.readBytes(values);
        Object obj = ObjectSerializerUtils.deSerializer(values);
        out.add(obj);
    }
}