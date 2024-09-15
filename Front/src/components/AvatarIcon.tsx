import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"


export function AvatarIcon(){
    return(
        <Avatar className="w-50 h-[300px]"> 
        <AvatarImage src="https://github.com/shadcn.png" />
        <AvatarFallback>CN</AvatarFallback>
      </Avatar>
      
    );

}