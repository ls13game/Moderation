const Discord = require("discord.js");
const client = new Discord.Client();
const DBL = require("dblapi.js");
const dbl = new DBL('Your discordbots.org token');

client.on('ready', () => {
    setInterval(() => {
        dbl.postStats(client.guilds.size);
    }, 1800000);
});

client.on('ready', () => {
    setInterval(() => {
        dbl.postStats(client.guilds.size, client.shards.Id, client.shards.total);
    }, 1800000);
});

dbl.getStats("378926376910192640")

dbl.getVotes(true)

dbl.hasVoted("129908908096487424")