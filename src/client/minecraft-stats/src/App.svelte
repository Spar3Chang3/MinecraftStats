<script>
  import { Client } from '@stomp/stompjs';
  import { WebSocket } from 'ws';

  let serverDiv;
  const port = "sigma_port"
  let ip = `edservemc.com:${port}`;

  function scrollFromButton() {
    serverDiv.scrollIntoView({behavior: "smooth",
                              block: "center"});
  }

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
      <div class="header-background">
        <div class="back-background"></div>
        <div class="back-foreground"></div>
      </div>
      <div class="header-text">
        Play EdsMCServe <button on:click={scrollFromButton}>HERE</button>
      </div>
    </header>

    <section class="console-container">
      <div class="console">
        <div class="console-content">
          {#each logs as log}
            <p>{log}</p>
          {/each}
        </div>
      </div>
    </section>

    <section class="heap-graph">

    </section>

    <section class="server-join-info" bind:this={serverDiv}>
      <h2>Server IP: {ip}</h2>
    </section>

    <footer>

    </footer>
  </div>
</main>

<style>

  header {
    display: flex;
    flex-direction: column;
    align-items: center;
    position: relative;
    height: 15vh;
    width: 100vw;

    .header-text {
      display: inline-flex;
      align-items: center;
      z-index: 2;
      font-size: 2rem;
      height: fit-content;
      width: fit-content;
      padding: 0.5rem;
      border-radius: 0 0 24px 24px;

      background-image: url("./assets/gilded_blackstone.png");
      background-repeat: repeat-x;
      background-position: center;
      background-size: contain;

      text-shadow: 4px 4px 8px;
    }

    margin-bottom: 2rem;
  }

  .header-background {
    position: relative;
    height: 10vh;
    width: 100%;

    .back-background {
      margin: 0;
      top: 0;
      height: 100%;
      width: 100%;
      z-index: 0;
      background-image: url("./assets/crying_obsidian.webp");
      background-repeat: repeat-x;
      background-size: contain;
      background-position: left;
    }

    .back-foreground {
      position: absolute;
      top: 0;
      height: 100%;
      width: 100%;
      z-index: 1;
      background-image: url("./assets/ed_banner.png");
      background-repeat: no-repeat;
      background-size: contain;
      background-position: center;
    }
  }

.console-container {
  display: inline-flex;
  height: 80vh;
  width: 100vw;
  justify-content: center;
  align-items: center;
}

.console {
  position: relative;
  display: flex;
  flex-direction: column;
  height: 80vh;
  width: 80vw;
  overflow: hidden;
  background-color: #1a1a1a;
  border-radius: 6px;
}

.console-content {
  display: flex;
  flex-direction: column;
  height: 95%;
  width: 95%;
  overflow: scroll;
  color: #f9f9f9;
}

  button {
    background-color: #646cff;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 12px;  /* Rounded corners */
    cursor: pointer;
    transition: background-color 100ms ease, transform 100ms ease;
    text-decoration: inherit;
    font-family: system-ui;
    margin: 0.5rem;
  }

  button:active {
    background-color: #5055cc;  /* Darker shade of #646cff */
    transform: scale(0.95);
  }

  .server-join-info {
    display: flex;
    flex-direction: column;
    width: 100vw;
    justify-content: center;
    align-items: center;
    margin-top: 2rem;
  }

</style>
