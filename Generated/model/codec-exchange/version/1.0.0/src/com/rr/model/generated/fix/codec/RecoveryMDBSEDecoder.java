/*******************************************************************************
 * Copyright (c) 2015 Low Latency Trading Limited  :  Author Richard Rose
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at	http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing,  software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *******************************************************************************/
package com.rr.model.generated.fix.codec;

import java.util.HashMap;
import java.util.Map;
import com.rr.core.codec.AbstractFixDecoderMD50;
import com.rr.core.codec.FixField;
import com.rr.core.lang.*;
import com.rr.core.model.*;
import com.rr.core.pool.SuperPool;
import com.rr.core.pool.SuperpoolManager;
import com.rr.model.internal.type.*;
import com.rr.core.codec.RuntimeDecodingException;
import com.rr.core.utils.StringUtils;
import com.rr.model.generated.fix.model.defn.FixDictionaryMD50;
import com.rr.model.generated.internal.events.factory.*;
import com.rr.model.generated.internal.events.impl.*;
import com.rr.model.generated.internal.events.interfaces.*;
import com.rr.model.generated.internal.type.*;
import com.rr.model.generated.internal.core.SizeType;

@SuppressWarnings( "unused" )

public final class RecoveryMDBSEDecoder extends AbstractFixDecoderMD50 {

   // Attrs

   // exec rpt only populated after all fields processed
   // only generate vars that are required

   // write String start and length vars required for ExecRpts


   // write value holders

    private final ViewString                  _lookup = new ViewString();

   // Pools

    private final SuperPool<HeartbeatImpl> _heartbeatPool = SuperpoolManager.instance().getSuperPool( HeartbeatImpl.class );
    private final HeartbeatFactory _heartbeatFactory = new HeartbeatFactory( _heartbeatPool );

    private final SuperPool<LogonImpl> _logonPool = SuperpoolManager.instance().getSuperPool( LogonImpl.class );
    private final LogonFactory _logonFactory = new LogonFactory( _logonPool );

    private final SuperPool<LogoutImpl> _logoutPool = SuperpoolManager.instance().getSuperPool( LogoutImpl.class );
    private final LogoutFactory _logoutFactory = new LogoutFactory( _logoutPool );

    private final SuperPool<SessionRejectImpl> _sessionRejectPool = SuperpoolManager.instance().getSuperPool( SessionRejectImpl.class );
    private final SessionRejectFactory _sessionRejectFactory = new SessionRejectFactory( _sessionRejectPool );

    private final SuperPool<ResendRequestImpl> _resendRequestPool = SuperpoolManager.instance().getSuperPool( ResendRequestImpl.class );
    private final ResendRequestFactory _resendRequestFactory = new ResendRequestFactory( _resendRequestPool );

    private final SuperPool<SequenceResetImpl> _sequenceResetPool = SuperpoolManager.instance().getSuperPool( SequenceResetImpl.class );
    private final SequenceResetFactory _sequenceResetFactory = new SequenceResetFactory( _sequenceResetPool );

    private final SuperPool<TestRequestImpl> _testRequestPool = SuperpoolManager.instance().getSuperPool( TestRequestImpl.class );
    private final TestRequestFactory _testRequestFactory = new TestRequestFactory( _testRequestPool );

    private final SuperPool<TradingSessionStatusImpl> _tradingSessionStatusPool = SuperpoolManager.instance().getSuperPool( TradingSessionStatusImpl.class );
    private final TradingSessionStatusFactory _tradingSessionStatusFactory = new TradingSessionStatusFactory( _tradingSessionStatusPool );

    private final SuperPool<MDRequestImpl> _mDRequestPool = SuperpoolManager.instance().getSuperPool( MDRequestImpl.class );
    private final MDRequestFactory _mDRequestFactory = new MDRequestFactory( _mDRequestPool );

    private final SuperPool<MassInstrumentStateChangeImpl> _massInstrumentStateChangePool = SuperpoolManager.instance().getSuperPool( MassInstrumentStateChangeImpl.class );
    private final MassInstrumentStateChangeFactory _massInstrumentStateChangeFactory = new MassInstrumentStateChangeFactory( _massInstrumentStateChangePool );

    private final SuperPool<SecurityStatusImpl> _securityStatusPool = SuperpoolManager.instance().getSuperPool( SecurityStatusImpl.class );
    private final SecurityStatusFactory _securityStatusFactory = new SecurityStatusFactory( _securityStatusPool );

    private final SuperPool<SecurityDefinitionImpl> _securityDefinitionPool = SuperpoolManager.instance().getSuperPool( SecurityDefinitionImpl.class );
    private final SecurityDefinitionFactory _securityDefinitionFactory = new SecurityDefinitionFactory( _securityDefinitionPool );

    private final SuperPool<MDIncRefreshImpl> _mDIncRefreshPool = SuperpoolManager.instance().getSuperPool( MDIncRefreshImpl.class );
    private final MDIncRefreshFactory _mDIncRefreshFactory = new MDIncRefreshFactory( _mDIncRefreshPool );

    private final SuperPool<MDSnapshotFullRefreshImpl> _mDSnapshotFullRefreshPool = SuperpoolManager.instance().getSuperPool( MDSnapshotFullRefreshImpl.class );
    private final MDSnapshotFullRefreshFactory _mDSnapshotFullRefreshFactory = new MDSnapshotFullRefreshFactory( _mDSnapshotFullRefreshPool );

    private final SuperPool<SecurityDefinitionUpdateImpl> _securityDefinitionUpdatePool = SuperpoolManager.instance().getSuperPool( SecurityDefinitionUpdateImpl.class );
    private final SecurityDefinitionUpdateFactory _securityDefinitionUpdateFactory = new SecurityDefinitionUpdateFactory( _securityDefinitionUpdatePool );

    private final SuperPool<ProductSnapshotImpl> _productSnapshotPool = SuperpoolManager.instance().getSuperPool( ProductSnapshotImpl.class );
    private final ProductSnapshotFactory _productSnapshotFactory = new ProductSnapshotFactory( _productSnapshotPool );


   // Constructors
    public RecoveryMDBSEDecoder() {
        this( FixVersion.MDFix5_0._major, FixVersion.MDFix5_0._minor );
    }

    public RecoveryMDBSEDecoder( byte major, byte minor ) {
        super( major, minor );
    }

   // decode methods

    @Override
    protected final Message doMessageDecode() {
        // get message type field
        if ( _fixMsg[_idx] != '3' || _fixMsg[_idx+1] != '5' || _fixMsg[_idx+2] != '=' )
            throwDecodeException( "Fix Messsage missing message type" );
        _idx += 3;

        byte msgType = _fixMsg[ _idx ];
        switch( msgType ) {
        case '0':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeHeartbeat();
        case 'A':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeLogon();
        case '5':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeLogout();
        case '3':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeSessionReject();
        case '2':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeResendRequest();
        case '4':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeSequenceReset();
        case '1':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeTestRequest();
        case 'h':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeTradingSessionStatus();
        case 'V':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeMDRequest();
        case 'C':
            {
                byte msgType2 = _fixMsg[ _idx+1 ];
                if ( msgType2 != 'O' ) {
                    throwDecodeException( "Unsupported fix message type " + (char)msgType + (char)msgType2 );
                }
                _idx += 3;
                return decodeMassInstrumentStateChange();
            }
        case 'f':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeSecurityStatus();
        case 'd':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeSecurityDefinition();
        case 'X':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeMDIncRefresh();
        case 'W':
            if ( _fixMsg[_idx+1 ] != FixField.FIELD_DELIMITER ) { // 2 byte message type
                throwDecodeException( "Unsupported fix message type " + _fixMsg[_idx] + _fixMsg[_idx+1] );
            }
            _idx += 2;
            return decodeMDSnapshotFullRefresh();
        case 'B':
          {
            byte msgType2 = _fixMsg[ _idx+1 ];
            switch( msgType2 ) {
            case 'P':
                if ( _fixMsg[_idx+2 ] != FixField.FIELD_DELIMITER ) {
                    throwDecodeException( "Unsupported fix message type " + (char)msgType + (char)msgType2 + (char)_fixMsg[_idx+2 ] );
                }
                _idx += 3;
                return decodeSecurityDefinitionUpdate();
            case 'U':
                if ( _fixMsg[_idx+2 ] != FixField.FIELD_DELIMITER ) {
                    throwDecodeException( "Unsupported fix message type " + (char)msgType + (char)msgType2 + (char)_fixMsg[_idx+2 ] );
                }
                _idx += 3;
                return decodeProductSnapshot();
            }
            _idx += 3;
            throwDecodeException( "Unsupported fix message type " + msgType );
            return null;
          }
        case '6':
        case '7':
        case '8':
        case '9':
        case ':':
        case ';':
        case '<':
        case '=':
        case '>':
        case '?':
        case '@':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'Y':
        case 'Z':
        case '[':
        case '\\':
        case ']':
        case '^':
        case '_':
        case '`':
        case 'a':
        case 'b':
        case 'c':
        case 'e':
        case 'g':
            break;
        }
        _idx += 2;
        throwDecodeException( "Unsupported fix message type " + msgType );
        return null;
    }


    public final Message decodeHeartbeat() {
        final HeartbeatImpl msg = _heartbeatFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.testReqID:         // tag112
                    start = _idx;
                    valLen = getValLength();
                    msg.setTestReqID( _fixMsg, start, valLen );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeLogon() {
        final LogonImpl msg = _logonFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                start = _idx;
                valLen = getValLength();
                msg.setSenderCompId( _fixMsg, start, valLen );
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                start = _idx;
                valLen = getValLength();
                msg.setTargetCompId( _fixMsg, start, valLen );
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.EncryptMethod:         // tag98
                    msg.setEncryptMethod( EncryptMethod.getVal( _fixMsg[_idx++] ) );
                    break;
                case FixDictionaryMD50.heartBtInt:         // tag108
                    msg.setHeartBtInt( getIntVal() );
                    break;
                case FixDictionaryMD50.RawDataLen:         // tag95
                    msg.setRawDataLen( getIntVal() );
                    break;
                case FixDictionaryMD50.RawData:         // tag96
                    start = _idx;
                    valLen = getValLength();
                    msg.setRawData( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.ResetSeqNumFlag:         // tag141
                    msg.setResetSeqNumFlag( (_fixMsg[_idx++]=='Y') ? true : false );
                    break;
                case FixDictionaryMD50.NextExpectedMsgSeqNum:         // tag789
                    msg.setNextExpectedMsgSeqNum( getIntVal() );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeLogout() {
        final LogoutImpl msg = _logoutFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                start = _idx;
                valLen = getValLength();
                msg.setSenderCompId( _fixMsg, start, valLen );
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                start = _idx;
                valLen = getValLength();
                msg.setTargetCompId( _fixMsg, start, valLen );
                break;
            case FixDictionaryMD50.Text:         // tag58
                start = _idx;
                valLen = getValLength();
                msg.setText( _fixMsg, start, valLen );
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                getValLength();
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeSessionReject() {
        final SessionRejectImpl msg = _sessionRejectFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.RefSeqNum:         // tag45
                msg.setRefSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case FixDictionaryMD50.Text:         // tag58
                start = _idx;
                valLen = getValLength();
                msg.setText( _fixMsg, start, valLen );
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.RefTagID:         // tag371
                    msg.setRefTagID( getIntVal() );
                    break;
                case FixDictionaryMD50.RefMsgType:         // tag372
                    start = _idx;
                    valLen = getValLength();
                    msg.setRefMsgType( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.SessionRejectReason:         // tag373
                    start = _idx;
                    valLen = getValLength();
                    msg.setSessionRejectReason( SessionRejectReason.getVal( _fixMsg, start, valLen ) );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeResendRequest() {
        final ResendRequestImpl msg = _resendRequestFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.BeginSeqNo:         // tag7
                msg.setBeginSeqNo( getIntVal() );
                break;
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.EndSeqNo:         // tag16
                msg.setEndSeqNo( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                getValLength();
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeSequenceReset() {
        final SequenceResetImpl msg = _sequenceResetFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.NewSeqNo:         // tag36
                msg.setNewSeqNo( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.GapFillFlag:         // tag123
                    msg.setGapFillFlag( (_fixMsg[_idx++]=='Y') ? true : false );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeTestRequest() {
        final TestRequestImpl msg = _testRequestFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.testReqID:         // tag112
                    start = _idx;
                    valLen = getValLength();
                    msg.setTestReqID( _fixMsg, start, valLen );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeTradingSessionStatus() {
        final TradingSessionStatusImpl msg = _tradingSessionStatusFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case FixDictionaryMD50.TransactTime:         // tag60
                msg.setTransactTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.marketSegmentId:         // tag1300
                    msg.setMarketSegmentID( getIntVal() );
                    break;
                case FixDictionaryMD50.TradingSessionID:         // tag336
                    msg.setTradingSessionID( TradingSessionID.getVal( _fixMsg[_idx++] ) );
                    break;
                case FixDictionaryMD50.TradingSessionSubID:         // tag625
                    msg.setTradingSessionSubID( TradingSessionSubID.getVal( _fixMsg[_idx++] ) );
                    break;
                case FixDictionaryMD50.TradSesStatus:         // tag340
                    msg.setTradSesStatus( TradSesStatus.getVal( _fixMsg[_idx++] ) );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeMDRequest() {
        final MDRequestImpl msg = _mDRequestFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case FixDictionaryMD50.SendingTime:         // SKIP tag52
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.mdReqId:         // tag262
                    start = _idx;
                    valLen = getValLength();
                    msg.setMdReqId( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.subsReqType:         // tag263
                    msg.setSubsReqType( SubsReqType.getVal( _fixMsg[_idx++] ) );
                    break;
                case FixDictionaryMD50.marketDepth:         // tag264
                    msg.setMarketDepth( getIntVal() );
                    break;
                case FixDictionaryMD50.numRelatedSym:         // tag146
                    int symbolRepeatingGrpNum = getIntVal(); // past delimiter
                    msg.setNumRelatedSym( symbolRepeatingGrpNum );
                    if ( symbolRepeatingGrpNum > 0 ) {
                        _idx++; // past delimiter IF subgroups exist
                        processSymbolRepeatingGrps( msg, symbolRepeatingGrpNum );
                        continue; // ALREADY HAVE NEXT TAG MUST RE-EVALUATE
                    }
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeMassInstrumentStateChange() {
        final MassInstrumentStateChangeImpl msg = _massInstrumentStateChangeFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.SendingTime:         // tag52
                msg.setSendingTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case FixDictionaryMD50.TransactTime:         // tag60
                msg.setTransactTime( getMSFromStartDayUTCFromBSEFastFix() );
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case FixDictionaryMD50.securityIDSource:         // SKIP tag22
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.securityID:         // SKIP tag48
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.marketSegmentId:         // tag1300
                    msg.setMarketSegmentID( getIntVal() );
                    break;
                case FixDictionaryMD50.instrumentScopeProductComplex:         // tag1544
                    msg.setInstrumentScopeProductComplex( getIntVal() );
                    break;
                case FixDictionaryMD50.securityMassTradingStatus:         // tag1679
                    msg.setSecurityMassTradingStatus( getIntVal() );
                    break;
                case FixDictionaryMD50.numRelatedSym:         // tag146
                    int secMassStatGrpNum = getIntVal(); // past delimiter
                    msg.setNumRelatedSym( secMassStatGrpNum );
                    if ( secMassStatGrpNum > 0 ) {
                        _idx++; // past delimiter IF subgroups exist
                        processSecMassStatGrps( msg, secMassStatGrpNum );
                        continue; // ALREADY HAVE NEXT TAG MUST RE-EVALUATE
                    }
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeSecurityStatus() {
        final SecurityStatusImpl msg = _securityStatusFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.securityIDSource:         // tag22
                msg.setSecurityIDSource( SecurityIDSource.getVal( _fixMsg[_idx++] ) );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.securityID:         // tag48
                msg.setSecurityID( getLongVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.Symbol:         // tag55
                start = _idx;
                valLen = getValLength();
                msg.setSymbol( _fixMsg, start, valLen );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case FixDictionaryMD50.SendingTime:         // SKIP tag52
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.TradeDate:         // tag75
                    msg.setTradeDate( getIntVal() );
                    break;
                case FixDictionaryMD50.highPx:         // tag332
                    msg.setHighPx( getDoubleVal() );
                    break;
                case FixDictionaryMD50.lowPx:         // tag333
                    msg.setLowPx( getDoubleVal() );
                    break;
                case FixDictionaryMD50.securityTradingStatus:         // tag326
                    start = _idx;
                    valLen = getValLength();
                    msg.setSecurityTradingStatus( SecurityTradingStatus.getVal( _fixMsg, start, valLen ) );
                    break;
                case FixDictionaryMD50.haltReason:         // tag327
                    msg.setHaltReason( getIntVal() );
                    break;
                case FixDictionaryMD50.SecurityTradingEvent:         // tag1174
                    msg.setSecurityTradingEvent( getIntVal() );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        msg.getSecurityExchangeForUpdate().copy( _defaultExchange );
        return msg;
    }

    public final Message decodeSecurityDefinitionUpdate() {
        final SecurityDefinitionUpdateImpl msg = _securityDefinitionUpdateFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.Currency:         // tag15
                start = _idx;
                valLen = getValLength();
                msg.setCurrency( Currency.getVal( _fixMsg, start, valLen ) );
                break;
            case FixDictionaryMD50.securityIDSource:         // tag22
                msg.setSecurityIDSource( SecurityIDSource.getVal( _fixMsg[_idx++] ) );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.securityID:         // tag48
                msg.setSecurityID( getLongVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.Symbol:         // tag55
                start = _idx;
                valLen = getValLength();
                msg.setSymbol( _fixMsg, start, valLen );
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case FixDictionaryMD50.SendingTime:         // SKIP tag52
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                switch( _tag ) {
                case FixDictionaryMD50.totNumReports:         // tag911
                    msg.setTotNumReports( getIntVal() );
                    break;
                case FixDictionaryMD50.securityType:         // tag167
                    start = _idx;
                    valLen = getValLength();
                    msg.setSecurityType( SecurityType.getVal( _fixMsg, start, valLen ) );
                    break;
                case FixDictionaryMD50.noEvents:         // tag864
                    int secDefEventsNum = getIntVal(); // past delimiter
                    msg.setNoEvents( secDefEventsNum );
                    if ( secDefEventsNum > 0 ) {
                        _idx++; // past delimiter IF subgroups exist
                        processSecDefEvents( msg, secDefEventsNum );
                        continue; // ALREADY HAVE NEXT TAG MUST RE-EVALUATE
                    }
                    break;
                case FixDictionaryMD50.SecurityUpdateAction:         // tag980
                    msg.setSecurityUpdateAction( SecurityUpdateAction.getVal( _fixMsg[_idx++] ) );
                    break;
                case FixDictionaryMD50.noLegs:         // tag555
                    int secDefLegNum = getIntVal(); // past delimiter
                    msg.setNoLegs( secDefLegNum );
                    if ( secDefLegNum > 0 ) {
                        _idx++; // past delimiter IF subgroups exist
                        processSecDefLegs( msg, secDefLegNum );
                        continue; // ALREADY HAVE NEXT TAG MUST RE-EVALUATE
                    }
                    break;
                case FixDictionaryMD50.tradingReferencePrice:         // tag1150
                    msg.setTradingReferencePrice( getDoubleVal() );
                    break;
                case FixDictionaryMD50.highLimitPx:         // tag1149
                    msg.setHighLimitPx( getDoubleVal() );
                    break;
                case FixDictionaryMD50.lowLimitPx:         // tag1148
                    msg.setLowLimitPx( getDoubleVal() );
                    break;
                case FixDictionaryMD50.minPriceIncrement:         // tag969
                    msg.setMinPriceIncrement( getDoubleVal() );
                    break;
                case FixDictionaryMD50.minPriceIncrementAmount:         // tag1146
                    msg.setMinPriceIncrementAmount( getDoubleVal() );
                    break;
                case FixDictionaryMD50.securityGroup:         // tag1151
                    start = _idx;
                    valLen = getValLength();
                    msg.setSecurityGroup( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.securityDesc:         // tag107
                    start = _idx;
                    valLen = getValLength();
                    msg.setSecurityDesc( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.CFICode:         // tag461
                    start = _idx;
                    valLen = getValLength();
                    msg.setCFICode( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.underlyingProduct:         // tag462
                    start = _idx;
                    valLen = getValLength();
                    msg.setUnderlyingProduct( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.securityExchange:         // tag207
                    start = _idx;
                    valLen = getValLength();
                    msg.setSecurityExchange( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.noSecurityAltID:         // tag454
                    int securityAltIDNum = getIntVal(); // past delimiter
                    msg.setNoSecurityAltID( securityAltIDNum );
                    if ( securityAltIDNum > 0 ) {
                        _idx++; // past delimiter IF subgroups exist
                        processSecurityAltIDs( msg, securityAltIDNum );
                        continue; // ALREADY HAVE NEXT TAG MUST RE-EVALUATE
                    }
                    break;
                case FixDictionaryMD50.strikePrice:         // tag202
                    msg.setStrikePrice( getDoubleVal() );
                    break;
                case FixDictionaryMD50.strikeCurrency:         // tag947
                    start = _idx;
                    valLen = getValLength();
                    msg.setStrikeCurrency( Currency.getVal( _fixMsg, start, valLen ) );
                    break;
                case FixDictionaryMD50.settlCurrency:         // tag120
                    start = _idx;
                    valLen = getValLength();
                    msg.setSettlCurrency( Currency.getVal( _fixMsg, start, valLen ) );
                    break;
                case FixDictionaryMD50.minTradeVol:         // tag562
                    msg.setMinTradeVol( getLongVal() );
                    break;
                case FixDictionaryMD50.maxTradeVol:         // tag1140
                    msg.setMaxTradeVol( getLongVal() );
                    break;
                case FixDictionaryMD50.noSDFeedTypes:         // tag1141
                    int sDFeedTypeNum = getIntVal(); // past delimiter
                    msg.setNoSDFeedTypes( sDFeedTypeNum );
                    if ( sDFeedTypeNum > 0 ) {
                        _idx++; // past delimiter IF subgroups exist
                        processSDFeedTypes( msg, sDFeedTypeNum );
                        continue; // ALREADY HAVE NEXT TAG MUST RE-EVALUATE
                    }
                    break;
                case FixDictionaryMD50.maturityMonthYear:         // tag200
                    msg.setMaturityMonthYear( getLongVal() );
                    break;
                case FixDictionaryMD50.lastUpdateTime:         // tag779
                    msg.setLastUpdateTime( getLongVal() );
                    break;
                case FixDictionaryMD50.applID:         // tag1180
                    start = _idx;
                    valLen = getValLength();
                    msg.setApplID( _fixMsg, start, valLen );
                    break;
                case FixDictionaryMD50.displayFactor:         // tag9787
                    msg.setDisplayFactor( getDoubleVal() );
                    break;
                case FixDictionaryMD50.priceRatio:         // tag5770
                    msg.setPriceRatio( getDoubleVal() );
                    break;
                case FixDictionaryMD50.contractMultiplierType:         // tag1435
                    msg.setContractMultiplierType( getIntVal() );
                    break;
                case FixDictionaryMD50.contractMultiplier:         // tag231
                    msg.setContractMultiplier( getIntVal() );
                    break;
                case FixDictionaryMD50.openInterestQty:         // tag5792
                    msg.setOpenInterestQty( getIntVal() );
                    break;
                case FixDictionaryMD50.tradingReferenceDate:         // tag5796
                    msg.setTradingReferenceDate( getIntVal() );
                    break;
                case FixDictionaryMD50.minQty:         // tag110
                    msg.setMinQty( getIntVal() );
                    break;
                case FixDictionaryMD50.pricePrecision:         // tag1200
                    msg.setPricePrecision( getDoubleVal() );
                    break;
                default:
                    getValLength();
                    break;
                }
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }

    public final Message decodeProductSnapshot() {
        final ProductSnapshotImpl msg = _productSnapshotFactory.get();
        _tag = getTag();

        int start;
        int valLen;

        while( _tag != 0 ) {
            switch( _tag ) {
            case FixDictionaryMD50.CheckSum:         // tag10
                validateChecksum( getIntVal() );
                break;
            case FixDictionaryMD50.securityIDSource:         // tag22
                msg.setSecurityIDSource( SecurityIDSource.getVal( _fixMsg[_idx++] ) );
                break;
            case FixDictionaryMD50.MsgSeqNum:         // tag34
                msg.setMsgSeqNum( getIntVal() );
                break;
            case FixDictionaryMD50.securityID:         // tag48
                msg.setSecurityID( getLongVal() );
                break;
            case FixDictionaryMD50.SenderCompID:         // tag49
                getValLength();
                break;
            case FixDictionaryMD50.TargetCompID:         // tag56
                getValLength();
                break;
            case 1:         // SKIP tag1
            case 2:         // SKIP tag2
            case 3:         // SKIP tag3
            case 4:         // SKIP tag4
            case 5:         // SKIP tag5
            case 6:         // SKIP tag6
            case FixDictionaryMD50.BeginSeqNo:         // SKIP tag7
            case FixDictionaryMD50.BeginString:         // SKIP tag8
            case FixDictionaryMD50.BodyLength:         // SKIP tag9
            case 11:         // SKIP tag11
            case 12:         // SKIP tag12
            case 13:         // SKIP tag13
            case 14:         // SKIP tag14
            case FixDictionaryMD50.Currency:         // SKIP tag15
            case FixDictionaryMD50.EndSeqNo:         // SKIP tag16
            case 17:         // SKIP tag17
            case 18:         // SKIP tag18
            case 19:         // SKIP tag19
            case 20:         // SKIP tag20
            case 21:         // SKIP tag21
            case 23:         // SKIP tag23
            case 24:         // SKIP tag24
            case 25:         // SKIP tag25
            case 26:         // SKIP tag26
            case 27:         // SKIP tag27
            case 28:         // SKIP tag28
            case 29:         // SKIP tag29
            case 30:         // SKIP tag30
            case 31:         // SKIP tag31
            case 32:         // SKIP tag32
            case 33:         // SKIP tag33
            case FixDictionaryMD50.MsgType:         // SKIP tag35
            case FixDictionaryMD50.NewSeqNo:         // SKIP tag36
            case 37:         // SKIP tag37
            case 38:         // SKIP tag38
            case 39:         // SKIP tag39
            case 40:         // SKIP tag40
            case 41:         // SKIP tag41
            case 42:         // SKIP tag42
            case FixDictionaryMD50.PossDupFlag:         // SKIP tag43
            case FixDictionaryMD50.Price:         // SKIP tag44
            case FixDictionaryMD50.RefSeqNum:         // SKIP tag45
            case 46:         // SKIP tag46
            case 47:         // SKIP tag47
            case FixDictionaryMD50.SenderSubID:         // SKIP tag50
            case 51:         // SKIP tag51
            case FixDictionaryMD50.SendingTime:         // SKIP tag52
            case 53:         // SKIP tag53
            case FixDictionaryMD50.Side:         // SKIP tag54
            case FixDictionaryMD50.Symbol:         // SKIP tag55
            case FixDictionaryMD50.TargetSubID:         // SKIP tag57
            case FixDictionaryMD50.Text:         // SKIP tag58
            case 59:         // SKIP tag59
            case FixDictionaryMD50.TransactTime:         // SKIP tag60
                getValLength();
                break;
            default:
                getValLength();
                break;
            }
            _idx++; /* past delimiter */ 
            _tag = getTag();
        }

        return msg;
    }


   // SubGrps

    private final SuperPool<SymbolRepeatingGrpImpl> _symbolRepeatingGrpPool = SuperpoolManager.instance().getSuperPool( SymbolRepeatingGrpImpl.class );
    private final SymbolRepeatingGrpFactory _symbolRepeatingGrpFactory = new SymbolRepeatingGrpFactory( _symbolRepeatingGrpPool );

    private void processSymbolRepeatingGrps( MDRequestImpl parent, int numEntries ) {

       SymbolRepeatingGrpImpl msg = null;
       SymbolRepeatingGrpImpl lastUpdate = null;
       _tag = getTag();

       int start;
       int valLen;

       while( _tag != 0 ) {
           switch( _tag ) {

           case FixDictionaryMD50.Symbol:         // tag55
               msg = _symbolRepeatingGrpFactory.get();
               if ( lastUpdate == null ) {
                   parent.setSymbolGrp( msg );
               } else {
                   lastUpdate.setNext( msg );
               }
               lastUpdate = msg;
               start = _idx;
               valLen = getValLength();
               msg.setSymbol( _fixMsg, start, valLen );
               break;
           default: // out of sequence tag .. return
               return;
           }
           _idx++; // past delimiter
           _tag = getTag();
       }

       return;
    }

    private final SuperPool<SecMassStatGrpImpl> _secMassStatGrpPool = SuperpoolManager.instance().getSuperPool( SecMassStatGrpImpl.class );
    private final SecMassStatGrpFactory _secMassStatGrpFactory = new SecMassStatGrpFactory( _secMassStatGrpPool );


    @SuppressWarnings( "null" )
    private void processSecMassStatGrps( MassInstrumentStateChangeImpl parent, int numEntries ) {

       SecMassStatGrpImpl msg = null;
       SecMassStatGrpImpl lastUpdate = null;
       _tag = getTag();

       int start;
       int valLen;

       while( _tag != 0 ) {
           switch( _tag ) {

           case FixDictionaryMD50.securityID:         // tag48
               msg = _secMassStatGrpFactory.get();
               if ( lastUpdate == null ) {
                   parent.setInstState( msg );
               } else {
                   lastUpdate.setNext( msg );
               }
               lastUpdate = msg;
               start = _idx;
               valLen = getValLength();
               msg.setSecurityId( _fixMsg, start, valLen );
               break;
           case FixDictionaryMD50.securityIDSource:         // tag22
               msg.setSecurityIDSource( SecurityIDSource.getVal( _fixMsg[_idx++] ) );
               break;
           case FixDictionaryMD50.securityTradingStatus:         // tag326
               start = _idx;
               valLen = getValLength();
               msg.setSecurityTradingStatus( SecurityTradingStatus.getVal( _fixMsg, start, valLen ) );
               break;
           case FixDictionaryMD50.securityStatus:         // tag965
               msg.setSecurityStatus( (_fixMsg[_idx++]=='Y') ? true : false );
               break;
           default: // out of sequence tag .. return
               return;
           }
           _idx++; // past delimiter
           _tag = getTag();
       }

       return;
    }

    private final SuperPool<SecDefEventsImpl> _secDefEventsPool = SuperpoolManager.instance().getSuperPool( SecDefEventsImpl.class );
    private final SecDefEventsFactory _secDefEventsFactory = new SecDefEventsFactory( _secDefEventsPool );


    @SuppressWarnings( "null" )
    private void processSecDefEvents( SecurityDefinitionUpdateImpl parent, int numEntries ) {

       SecDefEventsImpl msg = null;
       SecDefEventsImpl lastUpdate = null;
       _tag = getTag();

       int start;
       int valLen;

       while( _tag != 0 ) {
           switch( _tag ) {

           case FixDictionaryMD50.eventType:         // tag865
               msg = _secDefEventsFactory.get();
               if ( lastUpdate == null ) {
                   parent.setEvents( msg );
               } else {
                   lastUpdate.setNext( msg );
               }
               lastUpdate = msg;
               msg.setEventType( getIntVal() );
               break;
           case FixDictionaryMD50.eventDate:         // tag866
               msg.setEventDate( getLongVal() );
               break;
           case FixDictionaryMD50.eventTime:         // tag1145
               msg.setEventTime( getLongVal() );
               break;
           default: // out of sequence tag .. return
               return;
           }
           _idx++; // past delimiter
           _tag = getTag();
       }

       return;
    }

    private final SuperPool<SecDefLegImpl> _secDefLegPool = SuperpoolManager.instance().getSuperPool( SecDefLegImpl.class );
    private final SecDefLegFactory _secDefLegFactory = new SecDefLegFactory( _secDefLegPool );


    @SuppressWarnings( "null" )
    private void processSecDefLegs( SecurityDefinitionUpdateImpl parent, int numEntries ) {

       SecDefLegImpl msg = null;
       SecDefLegImpl lastUpdate = null;
       _tag = getTag();

       int start;
       int valLen;

       while( _tag != 0 ) {
           switch( _tag ) {

           case FixDictionaryMD50.legSymbol:         // tag600
               msg = _secDefLegFactory.get();
               if ( lastUpdate == null ) {
                   parent.setLegs( msg );
               } else {
                   lastUpdate.setNext( msg );
               }
               lastUpdate = msg;
               start = _idx;
               valLen = getValLength();
               msg.setLegSymbol( _fixMsg, start, valLen );
               break;
           case FixDictionaryMD50.legSecurityDesc:         // tag620
               start = _idx;
               valLen = getValLength();
               msg.setLegSecurityDesc( _fixMsg, start, valLen );
               break;
           case FixDictionaryMD50.legRatioQty:         // tag623
               msg.setLegRatioQty( getIntVal() );
               break;
           case FixDictionaryMD50.legSide:         // tag624
               msg.setLegSide( Side.getVal( _fixMsg[_idx++] ) );
               break;
           case FixDictionaryMD50.legSecurityID:         // tag602
               msg.setLegSecurityID( getLongVal() );
               break;
           case FixDictionaryMD50.legSecurityIDSource:         // tag603
               msg.setLegSecurityIDSource( SecurityIDSource.getVal( _fixMsg[_idx++] ) );
               break;
           case FixDictionaryMD50.LegPrice:         // tag566
               getValLength(); // no model attribute, SKIP
               break;
           default: // out of sequence tag .. return
               return;
           }
           _idx++; // past delimiter
           _tag = getTag();
       }

       return;
    }

    private final SuperPool<SecurityAltIDImpl> _securityAltIDPool = SuperpoolManager.instance().getSuperPool( SecurityAltIDImpl.class );
    private final SecurityAltIDFactory _securityAltIDFactory = new SecurityAltIDFactory( _securityAltIDPool );


    @SuppressWarnings( "null" )
    private void processSecurityAltIDs( SecurityDefinitionUpdateImpl parent, int numEntries ) {

       SecurityAltIDImpl msg = null;
       SecurityAltIDImpl lastUpdate = null;
       _tag = getTag();

       int start;
       int valLen;

       while( _tag != 0 ) {
           switch( _tag ) {

           case FixDictionaryMD50.securityAltID:         // tag455
               msg = _securityAltIDFactory.get();
               if ( lastUpdate == null ) {
                   parent.setSecurityAltIDs( msg );
               } else {
                   lastUpdate.setNext( msg );
               }
               lastUpdate = msg;
               start = _idx;
               valLen = getValLength();
               msg.setSecurityAltID( _fixMsg, start, valLen );
               break;
           case FixDictionaryMD50.securityAltIDSource:         // tag456
               msg.setSecurityAltIDSource( SecurityIDSource.getVal( _fixMsg[_idx++] ) );
               break;
           default: // out of sequence tag .. return
               return;
           }
           _idx++; // past delimiter
           _tag = getTag();
       }

       return;
    }

    private final SuperPool<SDFeedTypeImpl> _sDFeedTypePool = SuperpoolManager.instance().getSuperPool( SDFeedTypeImpl.class );
    private final SDFeedTypeFactory _sDFeedTypeFactory = new SDFeedTypeFactory( _sDFeedTypePool );


    @SuppressWarnings( "null" )
    private void processSDFeedTypes( SecurityDefinitionUpdateImpl parent, int numEntries ) {

       SDFeedTypeImpl msg = null;
       SDFeedTypeImpl lastUpdate = null;
       _tag = getTag();

       int start;
       int valLen;

       while( _tag != 0 ) {
           switch( _tag ) {

           case FixDictionaryMD50.feedType:         // tag1022
               msg = _sDFeedTypeFactory.get();
               if ( lastUpdate == null ) {
                   parent.setSDFeedTypes( msg );
               } else {
                   lastUpdate.setNext( msg );
               }
               lastUpdate = msg;
               start = _idx;
               valLen = getValLength();
               msg.setFeedType( _fixMsg, start, valLen );
               break;
           case FixDictionaryMD50.marketDepth:         // tag264
               msg.setMarketDepth( getIntVal() );
               break;
           default: // out of sequence tag .. return
               return;
           }
           _idx++; // past delimiter
           _tag = getTag();
       }

       return;
    }

   // transform methods
     