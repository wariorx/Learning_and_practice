class RockPaperScissors

  # Exceptions this class can raise:
  class NoSuchStrategyError < StandardError ; end

  def self.winner(player1, player2)
    # YOUR CODE HERE
    raise NoSuchStrategyError.new("Strategy must be one of R,P,S") if player1[1] =~ /[^[PSR]]/ || player2[1] =~ /[^[PSR]]/
    
    options = {:R => 1, :P => 2, :S => 3}
    
    choices = [options[player1[1].to_sym], options[player2[1].to_sym]] #store numeric values into an array of choices
    
    if choices[0] == 3 && choices[1] == 1
      return player2
    elsif choices[0] == choices[1] || choices[0] > choices[1] || (choices[0] == 1 && choices[1] == 3)
      return player1
    else
      return player2
    end
    
  end

  def self.tournament_winner(tournament)
    
    return winner(tournament[0], tournament[1]) if tournament[0][0].is_a? String
    return winner(tournament_winner(tournament[0]), tournament_winner(tournament[1]))
  
  end

end
