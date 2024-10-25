<script>
  import { Client } from '@stomp/stompjs';

  let logs = [];

  const stompClient = new Client({
    brokerURL: 'ws://#',
    onConnect: () => {
      stompClient.subscribe('/topic/log', message => {
        logs = [...logs, JSON.parse(message.body).content];
      });
      let logMessage = document.getElementById('console-content');
      logMessage.scrollTop = logMessage.scrollHeight;
    }
  });

  stompClient.activate();
</script>

<main>
  <div class="content">
    <header>
      Play EdsMCServe <a href="#">HERE</a>
    </header>

    <section>
      <div class="console">
        <div class="console-content">
          {#each logs as log}
            <p>{log}</p>
          {/each}
        </div>
      </div>
    </section>

    <footer>

    </footer>
  </div>
</main>

<style>
main {
  position: relative;
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
}

.console {
  position: relative;
  display: flex;
  flex-direction: column;
  height: 60vh;
  width: 60vw;
  overflow: hidden;
  background-color: #1a1a1a;
  border-radius: 6px;
}

.console-content {
  display: flex;
  flex-direction: column;
  height: 90%;
  width: 90%;
  overflow: scroll;
  color: #f9f9f9;
}
</style>
