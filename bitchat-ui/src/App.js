import React, {useEffect, useRef, useState} from "react";
import SockJsClient from "react-stomp";
import {v4 as uuid} from 'uuid';

function App() {

    const [userId, setUserId] = useState([])
    const [messages, setMessages] = useState([])
    const [newMessage, setNewMessage] = useState('')
    let clientRef = useRef(null)

    useEffect(() => {
        setUserId(Math.trunc(Math.random() * 100));
    }, [])

    const sendMessage = () => {
        clientRef.sendMessage('/bitchat/messages', JSON.stringify({
            content: newMessage,
            idempotenceId: uuid(),
            authorId: userId
        }));
        setNewMessage('')
    }

    const handleMessage = msg => {
        setMessages([...messages, msg])
    }

    return <>
        <h1>BitChat</h1>
        <h3>(userId: {userId})</h3>
        <div>
            <SockJsClient url='http://localhost:8080/bitchat-ws' topics={['/topic/messages']}
                          onMessage={handleMessage}
                          ref={client => clientRef = client}/>
        </div>
        <input type='text' value={newMessage} onChange={event => setNewMessage(event.target.value)}/>
        <button onClick={sendMessage}>Отправить</button>
        {messages.map((message, index) =>
            <div key={index}>
                user {message.authorId}: {message.content} -- {message.time}
            </div>
        )}
    </>
}

export default App;
