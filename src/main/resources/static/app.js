let gameId = null;
let dealerHidden = true; // dealer kartı kapalı mı?

async function newGame() {
  const res = await fetch("/api/game/new", { method: "POST" });
  const data = await res.json();
  gameId = data.id;
  dealerHidden = true; // yeni oyunda dealer kartı kapalı
  render(data);
}

async function hit() {
  const res = await fetch(`/api/game/${gameId}/hit`, { method: "POST" });
  render(await res.json());
}

async function stand() {
  const res = await fetch(`/api/game/${gameId}/stand`, { method: "POST" });
  dealerHidden = false; // artık açılabilir
  const game = await res.json();

  //Kapalı kartı AÇ
  await flipHiddenDealerCard();

  // Yeni kartları yanına ekle
  await appendDealerCards(game.dealer.hand);

  if (game.finished) {
    document.getElementById("result").innerText = game.result;
  }
}

function renderDealerHidden(cards) {
  const container = document.getElementById("dealer-cards");
  container.innerHTML = "";

  // Kapalı kart (flip edilecek)
  const card = document.createElement("div");
  card.className = "card";

  card.innerHTML = `
    <div class="card-inner" id="dealer-hidden-card">
      <img class="card-front" src="cards/back.jpg">
      <img class="card-back" src="cards/${cards[0].rank}_${cards[0].suit}.jpg">
    </div>
  `;
  container.appendChild(card);

  //Açık kart
  if (cards.length > 1) {
    const open = document.createElement("img");
    open.src = `cards/${cards[1].rank}_${cards[1].suit}.jpg`;
    open.className = "card";
    container.appendChild(open);
  }
}

function render(game) {
  console.log("GAME:", game);
  console.log("PLAYER HAND:", game.player.hand);
  console.log("DEALER HAND:", game.dealer.hand);
  renderCards("player-cards", game.player.hand);
  if (dealerHidden) {
    renderDealerHidden(game.dealer.hand);
  } else {
    renderCards("dealer-cards", game.dealer.hand);
  }

  if (game.finished && !dealerHidden) {
    document.getElementById("result").innerText = game.result;
  }
}

async function appendDealerCards(cards) {
  const container = document.getElementById("dealer-cards");

  for (let i = 2; i < cards.length; i++) {
    await sleep(1000);

    //Kapalı kart ekle
    const wrapper = document.createElement("div");
    wrapper.className = "card";

    wrapper.innerHTML = `
      <div class="card-inner">
        <img class="card-front" src="cards/BACK.jpg">
        <img class="card-back" src="cards/${cards[i].rank}_${cards[i].suit}.jpg">
      </div>
    `;

    container.appendChild(wrapper);

    //Flip ile aç
    await sleep(400);
    wrapper.querySelector(".card-inner").classList.add("flip");
  }
}

async function flipHiddenDealerCard() {
  const hidden = document.getElementById("dealer-hidden-card");
  if (hidden) {
    hidden.classList.add("flip");
    await sleep(700);
  }
}

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function revealDealerCards(cards) {
  const container = document.getElementById("dealer-cards");
  container.innerHTML = "";

  for (let i = 0; i < cards.length; i++) {
    await sleep(1000); //kartlar arası gecikme

    const img = document.createElement("img");
    img.src = `cards/${cards[i].rank}_${cards[i].suit}.jpg`;
    img.className = "card";

    container.appendChild(img);
  }
}

function renderCards(containerId, cards) {
  const container = document.getElementById(containerId);
  container.innerHTML = "";

  if (!cards) return;

  cards.forEach((card) => {
    const img = document.createElement("img");
    img.src = `cards/${card.rank}_${card.suit}.jpg`;
    img.className = "card";
    container.appendChild(img);
  });
}
newGame();
